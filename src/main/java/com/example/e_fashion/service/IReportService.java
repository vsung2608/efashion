package com.example.e_fashion.service;

import java.math.BigDecimal;

public interface IReportService {
    BigDecimal calculatorRevenueThisMonth();

    int countOrderToday();
}
