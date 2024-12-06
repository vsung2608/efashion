package com.example.e_fashion.service.impl;

import com.example.e_fashion.dto.request.CategoryRequest;
import com.example.e_fashion.dto.response.CategoryResponse;
import com.example.e_fashion.mapper.CategoryMapper;
import com.example.e_fashion.repository.CategoryRepository;
import com.example.e_fashion.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public void saveCategory(CategoryRequest request) {
        var category = categoryMapper.toCategory(request);
        if(categoryRepository.existsByName(request.getName())){
            throw new RuntimeException("Thể loại " + request.getName() + " đã tồn tại.");
        };
        categoryRepository.save(category);
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toCategoryResponse)
                .toList();
    }
}
