package russianhackers.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TemplateController {

    @GetMapping("login")
    public String getLoginView() {
        //must be the same name as the html file in the resources > templates directory
        return "login";
    }

    @GetMapping("journals")
    public String getJournals() {
        //must be the same name as the html file in the resources > templates directory
        return "journals";
    }
}
