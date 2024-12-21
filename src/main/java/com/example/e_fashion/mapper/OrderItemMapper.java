package com.example.e_fashion.mapper;

import com.example.e_fashion.dto.response.OrderItemResponse;
import com.example.e_fashion.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    OrderItemResponse toOrderItemResponse(OrderItem orderItem);
}
