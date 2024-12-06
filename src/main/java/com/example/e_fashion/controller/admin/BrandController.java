package com.example.e_fashion.controller.admin;

import com.example.e_fashion.dto.request.BrandRequest;
import com.example.e_fashion.dto.response.ApiResponse;
import com.example.e_fashion.dto.response.BrandResponse;
import com.example.e_fashion.service.IBrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/brands")
@RequiredArgsConstructor
public class BrandController {
    private final IBrandService brandService;

    @GetMapping
    public String list(Model model){
        List<BrandResponse> brands = brandService.getAllBrands();
        model.addAttribute("brands", brands);
        return "views/admin/manage-data/manage-brand";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ApiResponse<BrandResponse> getToUpdate(@PathVariable("id") String id) {
        return ApiResponse.<BrandResponse>builder()
                .code(200)
                .message("success")
                .data(brandService.getOneBrand(id))
                .build();
    }

    @PostMapping
    @ResponseBody
    public ApiResponse<String> addProduct(@RequestBody BrandRequest request){
        brandService.saveBrand(request);
        return ApiResponse.<String>builder()
                .code(200)
                .message("Đã hợp tác với hãng thành công")
                .data("success")
                .build();
    }

    @PutMapping
    @ResponseBody
    public ApiResponse<String> updateProduct(@RequestBody BrandRequest request){
        brandService.saveBrand(request);
        return ApiResponse.<String>builder()
                .code(200)
                .message("Cập nhật thông tin hãng thành công")
                .data("success")
                .build();
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ApiResponse<String> deleteProduct(@PathVariable String id){
        brandService.stopWorkingBrand(id);
        return ApiResponse.<String>builder()
                .code(200)
                .message("Đã ngừng hợp tác với hãng sản phẩm này")
                .data("success")
                .build();
    }
}
