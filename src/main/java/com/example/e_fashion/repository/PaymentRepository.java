package com.example.e_fashion.repository;

import com.example.e_fashion.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, String> {
    Optional<Payment> findByOrderOrderId(String id);
}
