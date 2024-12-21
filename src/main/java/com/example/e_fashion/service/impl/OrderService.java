package com.example.e_fashion.service.impl;

import com.example.e_fashion.dto.response.OrderResponse;
import com.example.e_fashion.entity.Order;
import com.example.e_fashion.mapper.*;
import com.example.e_fashion.repository.OrderRepository;
import com.example.e_fashion.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public OrderResponse getById(String orderId) {
        var order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        return orderMapper.toOrderResponse(order);

    }

    @Override
    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toOrderResponse)
                .toList();
    }

}
