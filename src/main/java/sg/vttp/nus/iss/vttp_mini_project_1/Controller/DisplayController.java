package sg.vttp.nus.iss.vttp_mini_project_1.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class DisplayController {
    
    @GetMapping(path = {"/", "/index.html"})
    public String homepage() {
        return "index";
    }
}
