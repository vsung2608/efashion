package com.example.e_fashion.controller.admin;

import com.example.e_fashion.dto.response.OrderResponse;
import com.example.e_fashion.service.impl.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public String list(Model model){
        List<OrderResponse> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);

        return "views/admin/manage-data/manage-order";
    }

    @GetMapping("/{id}")
    public OrderResponse getById(@PathVariable String id){
        return orderService.getById(id);
    }
}
