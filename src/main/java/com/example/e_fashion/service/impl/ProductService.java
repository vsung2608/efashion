package com.example.e_fashion.service.impl;


import com.example.e_fashion.dto.request.ProductRequest;
import com.example.e_fashion.dto.response.ProductResponse;
import com.example.e_fashion.mapper.BrandMapper;
import com.example.e_fashion.mapper.CategoryMapper;
import com.example.e_fashion.mapper.ProductMapper;
import com.example.e_fashion.repository.BrandRepository;
import com.example.e_fashion.repository.CategoryRepository;
import com.example.e_fashion.repository.ProductRepository;
import com.example.e_fashion.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;

    private final ProductMapper productMapper;

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAllByDeletedFalse().stream()
                .map(productMapper::toProductResponse)
                .toList();
    }

    public ProductResponse getProductById(String id){
        var product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return productMapper.toProductResponse(product);
    }

    @Override
    public void saveProduct(ProductRequest request) {
        var product = productMapper.toProduct(request);
        var category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        var brand = brandRepository.findById(request.getBrandId())
                .orElseThrow(() -> new RuntimeException("Brand not found"));
        product.setCategory(category);
        product.setBrand(brand);
        product.setDeleted(false);

        productRepository.save(product);
    }

    @Override
    public void deleteProduct(String id) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setDeleted(true);
        productRepository.save(product);
    }

    @Override
    public List<ProductResponse> getAllByKeyword(String keyword) {
        return productRepository.findAllByName(keyword).stream()
                .map(productMapper::toProductResponse)
                .toList();
    }

    @Override
    public List<ProductResponse> getProductByBrandId(String brandId) {
        return productRepository.findByBrandBrandId(brandId).stream()
                .map(productMapper::toProductResponse)
                .toList();
    }
}
