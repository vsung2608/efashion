package com.example.e_fashion.repository;

import com.example.e_fashion.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findAllByDeletedFalse();

    @Query("SELECT p FROM Product p WHERE p.name LIKE %:name% AND p.deleted = false")
    List<Product> findAllByName(@Param("name") String name);

    List<Product> findByBrandBrandId(String brandId);
}
