package com.example.e_fashion.service.impl;

import com.example.e_fashion.repository.OrderRepository;
import com.example.e_fashion.service.IReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ReportService implements IReportService {
    private final OrderRepository orderRepository;

    @Override
    public BigDecimal calculatorRevenueThisMonth() {
        return orderRepository.getRevenueThisMonth() != null ? orderRepository.getRevenueThisMonth() : BigDecimal.ZERO;
    }

    @Override
    public int countOrderToday() {
        return orderRepository.countByDateToday();
    }
}
