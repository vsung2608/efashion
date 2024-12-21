package com.example.e_fashion.dto.response;

import com.example.e_fashion.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemResponse {

    private String orderItemId;

    private ProductResponse product;

    private int quantity;

    private BigDecimal price;
}
