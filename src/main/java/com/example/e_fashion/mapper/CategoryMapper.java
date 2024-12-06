package com.example.e_fashion.mapper;

import com.example.e_fashion.dto.request.CategoryRequest;
import com.example.e_fashion.dto.response.CategoryResponse;
import com.example.e_fashion.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toCategory(CategoryRequest request);

    CategoryResponse toCategoryResponse(Category category);
}
