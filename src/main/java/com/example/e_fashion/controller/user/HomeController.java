package com.example.e_fashion.controller.user;

import com.example.e_fashion.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/general")
public class HomeController {

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

    @GetMapping("/home-page")
    public String homepage(Model model){
        var authenticated = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(authenticated instanceof User) model.addAttribute("authenticated", true);
        else model.addAttribute("authenticated", false);

        System.out.println(SecurityContextHolder.getContext().getAuthentication().getDetails());

        return "views/user/home-page";
    }

    @GetMapping("/introduction")
    public String about(){
        return "views/user/introduction";
    }

    @GetMapping("/blogs")
    public String blogs(){
        return "views/user/blogs";
    }

    @GetMapping("/contact")
    public String contact(){
        return "views/user/contacts";
    }
}
