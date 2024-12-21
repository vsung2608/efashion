package com.example.e_fashion.mapper;

import com.example.e_fashion.dto.response.CartResponse;
import com.example.e_fashion.entity.Cart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ProductMapper.class)
public interface CartMapper {
    CartResponse toResponse(Cart cart);
}
