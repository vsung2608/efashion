package com.example.e_fashion.repository;

import com.example.e_fashion.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, String> {

    Optional<Brand> findById(String id);
}
