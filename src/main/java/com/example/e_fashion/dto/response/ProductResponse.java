package com.example.e_fashion.dto.response;

import com.example.e_fashion.entity.Brand;
import com.example.e_fashion.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private String productId;
    private String name;
    private String description;
    private BigDecimal price;
    private int stockQuantity;
    private String imageUrl;
    private CategoryResponse category;
    private BrandResponse brand;
    private int discount;
    private boolean deleted;
    private LocalDateTime createdAt;
    private BigDecimal promotionalPrice;
}
