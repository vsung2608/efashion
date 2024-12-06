package com.example.e_fashion.repository;

import com.example.e_fashion.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, String> {
    boolean existsByName(String name);

    Optional<Category> findById(String id);
}
