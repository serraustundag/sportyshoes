package ex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("dashboard") //local/dashboard
public class DashboardController {

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String open(Model model) {
        model.addAttribute("dashboard");
        return "dashboard";
    }
}
