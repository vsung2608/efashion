package com.example.e_fashion.mapper;

import com.example.e_fashion.dto.request.ProductRequest;
import com.example.e_fashion.dto.response.ProductResponse;
import com.example.e_fashion.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class, BrandMapper.class})
public interface ProductMapper {

    Product toProduct(ProductRequest request);

    ProductResponse toProductResponse(Product product);
}
