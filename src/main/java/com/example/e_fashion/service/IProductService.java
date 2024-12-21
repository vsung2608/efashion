package com.example.e_fashion.service;

import com.example.e_fashion.dto.request.ProductRequest;
import com.example.e_fashion.dto.response.ProductResponse;

import java.util.List;

public interface IProductService {

    List<ProductResponse> getAllProducts();

    ProductResponse getProductById(String id);

    void saveProduct(ProductRequest request);

    void deleteProduct(String id);

    List<ProductResponse> getAllByKeyword(String keyword);

    List<ProductResponse> getProductByBrandId(String brandId);
}
