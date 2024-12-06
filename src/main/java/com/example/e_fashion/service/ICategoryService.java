package com.example.e_fashion.service;

import com.example.e_fashion.dto.request.CategoryRequest;
import com.example.e_fashion.dto.response.CategoryResponse;

import java.util.List;

public interface ICategoryService {

    void saveCategory(CategoryRequest request);

    List<CategoryResponse> getAllCategories();
}
