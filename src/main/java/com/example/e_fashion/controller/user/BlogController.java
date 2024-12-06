package com.example.e_fashion.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/general")
public class BlogController {
    @GetMapping("/blog/detail")
    public String detail() {
        return "views/user/blog-details";
    }
}
