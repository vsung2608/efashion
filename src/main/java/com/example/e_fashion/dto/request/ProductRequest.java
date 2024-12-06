package com.example.e_fashion.dto.request;

import com.example.e_fashion.entity.Brand;
import com.example.e_fashion.entity.Category;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String productId;
    private String name;
    private String description;
    private BigDecimal price;
    private int stockQuantity;
    private String imageUrl;
    private String categoryId;
    private String brandId;
    @Size(min = 0, max = 100)
    private int discount;
}
