package com.example.e_fashion.dto.response;

import com.example.e_fashion.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private String orderId;

    private UserResponse user;

    private LocalDateTime orderDate;

    private int totalAmount;

    private String shippingAddress;

    private OrderStatus status;

    private List<OrderItemResponse> orderItems;

    private PaymentResponse payment;
}
