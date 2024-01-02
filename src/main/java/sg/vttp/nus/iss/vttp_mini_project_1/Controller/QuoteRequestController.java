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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import sg.vttp.nus.iss.vttp_mini_project_1.Model.QuoteRequest;
import sg.vttp.nus.iss.vttp_mini_project_1.Service.QuotationService;

@Controller
@RequestMapping("/quote")
public class QuoteRequestController {
    
    @Autowired
    QuotationService quoteSvc;

    @GetMapping
    public String landingPage(HttpServletResponse response) {
        response.setHeader("Cache-Control","no-cache, no-store, max-age=0, must-revalidate");
        response.setHeader("Pragma","no-cache");
        response.setHeader("Expires", "0");
        return "quote-main";
    }

    @PostMapping("/view")
    public String viewPage(@RequestParam String trackingNumber, @RequestParam String email, Model model,RedirectAttributes attributes) {
        boolean haveRequest = quoteSvc.hasRequest(trackingNumber);
        if(!haveRequest) {
            model.addAttribute("validate", haveRequest);
            return "quote-main";
        }
        QuoteRequest quoteRequest = quoteSvc.getRequest(trackingNumber);
        boolean correctEmail = quoteRequest.getEmail().equals(email);
        if(!correctEmail) {
            model.addAttribute("validate", false);
            return "quote-main";
        }
        attributes.addFlashAttribute("quoteRequest", quoteRequest);
        return "redirect:/quote/view/" + trackingNumber;
    }

    @GetMapping("/view/{id}")
    public String viewRequest(@PathVariable String id, Model model, HttpServletResponse response) {
        
        response.setHeader("Cache-Control","no-cache, no-store, max-age=0, must-revalidate");
        response.setHeader("Pragma","no-cache");
        response.setHeader("Expires", "0");
        model.addAttribute("idNumber", id);
        if (!model.containsAttribute("quoteRequest")) {
            model.addAttribute("validate", false);
            return "redirect:/quote";
        }
        
        return "quote-view";
    } 

    @GetMapping("/register")
    public String requestPage(Model model, HttpServletResponse response) {
        response.setHeader("Cache-Control","no-cache, no-store, max-age=0, must-revalidate");
        response.setHeader("Pragma","no-cache");
        response.setHeader("Expires", "0");
        model.addAttribute("quoteRequest", new QuoteRequest());
        return "quote-request";
    }



    @PostMapping("/register")
    public String registerRequest(@Valid @ModelAttribute QuoteRequest quoteRequest, BindingResult result, Model model, RedirectAttributes attributes, HttpServletResponse response) {
        // adding below will cause content form resubmission and refreshing will redisplay previous submission data, only add for get.
        // response.setHeader("Cache-Control","no-cache, no-store, max-age=0, must-revalidate");
        // response.setHeader("Pragma","no-cache");
        // response.setHeader("Expires", "0");
        if(result.hasErrors()) {
            return "quote-request";
        }

        System.out.println(quoteRequest);
        String id = quoteSvc.createRequest(quoteRequest);

       
        attributes.addFlashAttribute("quoteRequest", quoteRequest);

        return "redirect:/quote/view/" + id;
    }
}
