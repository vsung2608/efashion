package com.example.e_fashion.service.impl;

import com.example.e_fashion.dto.request.BrandRequest;
import com.example.e_fashion.dto.response.BrandResponse;
import com.example.e_fashion.mapper.BrandMapper;
import com.example.e_fashion.repository.BrandRepository;
import com.example.e_fashion.service.IBrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService implements IBrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    @Override
    public List<BrandResponse> getAllBrands() {
        return brandRepository.findAll().stream()
                .map(brandMapper::toBrandResponse)
                .toList();
    }

    @Override
    public void saveBrand(BrandRequest request) {
        var brand = brandMapper.toBrand(request);
        brandRepository.save(brand);
    }

    @Override
    public BrandResponse getOneBrand(String id) {
        var brand = brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhãn hàng này"));
        return brandMapper.toBrandResponse(brand);
    }

    @Override
    public void stopWorkingBrand(String id) {
        var brand = brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhãn hàng này"));
        brand.setStatus("Chấm dứt");
        brandRepository.save(brand);
    }
}
