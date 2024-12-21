package com.example.e_fashion.dto.response;

import com.example.e_fashion.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {

    private String paymentId;

    private LocalDateTime paymentDate;

    private String paymentMethod;

    private BigDecimal amount;

    private String paymentStatus;
}
