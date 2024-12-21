package com.example.e_fashion.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web")
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/home-page")
    public String homepage(Authentication authentication, Model model){
        if(authentication == null)model.addAttribute("logined", false);
        else model.addAttribute("logined", true);
        return "views/user/home-page";
    }

    @GetMapping("/introduction")
    public String about(Authentication authentication, Model model){
        if(authentication == null)model.addAttribute("logined", false);
        else model.addAttribute("logined", true);
        return "views/user/introduction";
    }

    @GetMapping("/blogs")
    public String blogs(Authentication authentication, Model model){
        if(authentication == null)model.addAttribute("logined", false);
        else model.addAttribute("logined", true);
        return "views/user/blogs";
    }

    @GetMapping("/contact")
    public String contact(Authentication authentication, Model model){
        if(authentication == null)model.addAttribute("logined", false);
        else model.addAttribute("logined", true);
        return "views/user/contacts";
    }

    @GetMapping("/blogs/detail")
    public String blogsDetail(Authentication authentication, Model model){
        if(authentication == null)model.addAttribute("logined", false);
        else model.addAttribute("logined", true);
        return "views/user/blog-details";
    }
}
