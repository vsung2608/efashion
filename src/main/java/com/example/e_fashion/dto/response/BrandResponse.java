package com.example.e_fashion.dto.response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BrandResponse {
    private String brandId;

    private String name;

    private String description;

    private String logoUrl;

    private String phone;

    private String address;

    private String email;

    private String website;

    private String status;
}
