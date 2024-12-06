package com.example.e_fashion.repository;

import com.example.e_fashion.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findAllByDeletedFalse();
}
