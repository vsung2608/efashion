package com.example.e_fashion.mapper;

import com.example.e_fashion.dto.response.PaymentResponse;
import com.example.e_fashion.entity.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    PaymentResponse toPaymentResponse(Payment payment);
}
