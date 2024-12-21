package com.example.e_fashion.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/general/")
public class GneneralController {
    @GetMapping("/login")
    public String login() {
        return "views/general/login";
    }

    @GetMapping("/register")
    public String register(){
        return "views/general/register";
    }

    @GetMapping("/confirmation")
    public String confirmation(){
        return "views/general/confirmation";
    }
}
