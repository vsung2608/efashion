package com.example.e_fashion.mapper;

import com.example.e_fashion.dto.response.OrderResponse;
import com.example.e_fashion.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface OrderMapper {

    OrderResponse toOrderResponse(Order order);
}
