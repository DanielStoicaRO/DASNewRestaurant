package main.das.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/", "/index"})
    public String getLandingPage() {
        return "index";
    }

//    @GetMapping("/login")
//    public String getLoginPage() {
//        return "login";
//    }
//    @GetMapping("/register")
//    public String getRegisterPage() {
//        return "register";
//    }


}
