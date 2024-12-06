package com.example.e_fashion.mapper;

import com.example.e_fashion.dto.request.BrandRequest;
import com.example.e_fashion.dto.response.BrandResponse;
import com.example.e_fashion.entity.Brand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrandMapper {
    BrandResponse toBrandResponse(Brand brand);

    Brand toBrand(BrandRequest request);
}
