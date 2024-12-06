package com.example.e_fashion.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard() {
        return "views/admin/dashboard";
    }
}
