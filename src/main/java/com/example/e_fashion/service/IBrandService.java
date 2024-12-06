package com.example.e_fashion.service;

import com.example.e_fashion.dto.request.BrandRequest;
import com.example.e_fashion.dto.response.BrandResponse;

import java.util.List;

public interface IBrandService {
    List<BrandResponse> getAllBrands();

    void saveBrand(BrandRequest request);

    BrandResponse getOneBrand(String id);

    void stopWorkingBrand(String id);
}
