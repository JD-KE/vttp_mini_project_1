package sg.vttp.nus.iss.vttp_mini_project_1.Controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.vttp.nus.iss.vttp_mini_project_1.Model.NodalEnergyPrice;
import sg.vttp.nus.iss.vttp_mini_project_1.Service.ECISPriceService;

@Controller
@RequestMapping(path = "/topics")
public class TopicsController {
    @Autowired
    ECISPriceService priceSvc;

    @GetMapping(path = {"","/list"})
    public String listTopicsPage() {
        return "topics-list";
    }
    
    @GetMapping(path = "/sellback")
    public String sellbackPage(@RequestParam(value = "submitDate", required = false) Optional<String> dateOpt, Model model) {

        String availableDate = LocalDate.now().minusDays(10L).toString();
        String safeAvailableDate = LocalDate.now().minusDays(13L).toString();
        String date;

        if (dateOpt.isEmpty()) {
            date = safeAvailableDate;
        } else {
            date = dateOpt.get();
        }

        List<NodalEnergyPrice> ECISdata;

        if (priceSvc.validateDate(date)) {
            ECISdata = priceSvc.getECISPrice(date);
            model.addAttribute("graphDate", date); 
        } else {
            ECISdata = priceSvc.getECISPrice(safeAvailableDate);
            model.addAttribute("graphDate", safeAvailableDate); 
        }

        Map<Integer,String> graphData = new HashMap<>();
        for (NodalEnergyPrice data: ECISdata) {
            graphData.put(Integer.valueOf(data.getPeriod()), 
            Double.valueOf((Double.valueOf(data.getPrice())/1000)).toString());
        }

        model.addAttribute("graphData", graphData);
        model.addAttribute("availableDate", availableDate);
        model.addAttribute("validate", priceSvc.validateDate(date));
               

        return "topics-sellback";
    }
    
}
