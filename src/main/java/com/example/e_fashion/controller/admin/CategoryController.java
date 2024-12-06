package com.example.e_fashion.controller.admin;

import com.example.e_fashion.dto.request.CategoryRequest;
import com.example.e_fashion.dto.response.ApiResponse;
import com.example.e_fashion.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/categories")
public class CategoryController {

    private final ICategoryService categoryService;

    @PostMapping
    @ResponseBody
    public ApiResponse<String> createCategory(@RequestBody CategoryRequest request){
        categoryService.saveCategory(request);
        return ApiResponse.<String>builder()
                .code(200)
                .message("Thêm thể loại " + request.getName() + " thành công!")
                .data("success")
                .build();
    }
}
