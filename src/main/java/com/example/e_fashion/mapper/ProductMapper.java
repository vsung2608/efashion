package com.example.e_fashion.mapper;

import com.example.e_fashion.dto.request.ProductRequest;
import com.example.e_fashion.dto.response.ProductResponse;
import com.example.e_fashion.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "category", ignore = true)
    @Mapping(target = "brand", ignore = true)
    Product toProduct(ProductRequest request);

    @Mapping(target = "category", ignore = true)
    @Mapping(target = "brand", ignore = true)
    ProductResponse toProductResponse(Product product);
}
