package com.example.e_fashion.repository;

import com.example.e_fashion.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Date;

public interface OrderRepository extends JpaRepository<Order, String> {


    @Query("""
            SELECT SUM(o.totalAmount) FROM Order o
            WHERE o.status = 'SHIPPED' AND MONTH(o.orderDate) = MONTH(CURRENT TIME)
            """)
    BigDecimal getRevenueThisMonth();

    @Query("""
            SELECT COUNT(o) FROM Order o
            WHERE DATE(o.orderDate) = CURRENT DATE
            """)
    int countByDateToday();
}
