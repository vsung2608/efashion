package com.example.e_fashion.dto.response;

import com.example.e_fashion.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse {

    private String cartId;

    @JsonIgnore
    private User user;

    private ProductResponse product;

    private int quantity;

    private LocalDateTime addedAt;

}
