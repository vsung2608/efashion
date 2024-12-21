package com.example.e_fashion.service;

import com.example.e_fashion.dto.response.OrderResponse;
import com.example.e_fashion.entity.Order;
import com.example.e_fashion.entity.Payment;

import java.util.List;

public interface IOrderService {
    void saveOrder(Order order);

    OrderResponse getById(String orderId);

    List<OrderResponse> getAllOrders();
}
