package sg.vttp.nus.iss.vttp_mini_project_1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import sg.vttp.nus.iss.vttp_mini_project_1.Model.QuoteRequest;
import sg.vttp.nus.iss.vttp_mini_project_1.Service.QuotationService;

@Controller
@RequestMapping("/quote")
public class QuoteRequestController {
    
    @Autowired
    QuotationService quoteSvc;

    @GetMapping
    public String landingPage() {
        return "quotemain";
    }

    @GetMapping("/view")
    public String viewPage(@RequestParam String trackingNumber, Model model) {
        boolean haveRequest = quoteSvc.hasRequest(trackingNumber);
        if(!haveRequest) {
            model.addAttribute("validate", haveRequest);
            return "quotemain";
        }
        return "redirect:/quote/view/" + trackingNumber;
    }

    @GetMapping("/view/{id}")
    public String viewRequest(@PathVariable String id, Model model) {
        if (!quoteSvc.hasRequest(id)){
            model.addAttribute("validate", false);
            return "quotemain";
        }
        QuoteRequest quoteRequest = quoteSvc.getRequest(id);
        model.addAttribute("idNumber", id);
        model.addAttribute("quoteRequest", quoteRequest);
        return "quoteview";
    } 

    @GetMapping("/register")
    public String requestPage(Model model) {
        model.addAttribute("quoteRequest", new QuoteRequest());
        return "quoterequest";
    }



    @PostMapping("/register")
    public String registerRequest(@Valid @ModelAttribute QuoteRequest quoteRequest, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "quoterequest";
        }

        System.out.println(quoteRequest);
        String id = quoteSvc.createRequest(quoteRequest);

        model.addAttribute("idNumber", id);
        model.addAttribute("quoteRequest", quoteRequest);

        return "quoteview";
    }
}
