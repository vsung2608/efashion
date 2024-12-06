package com.example.e_fashion.controller.admin;

import com.example.e_fashion.dto.request.ProductRequest;
import com.example.e_fashion.dto.response.ApiResponse;
import com.example.e_fashion.dto.response.BrandResponse;
import com.example.e_fashion.dto.response.CategoryResponse;
import com.example.e_fashion.dto.response.ProductResponse;
import com.example.e_fashion.service.IBrandService;
import com.example.e_fashion.service.ICategoryService;
import com.example.e_fashion.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/products")
public class ProductController {

    private final IProductService productService;
    private final ICategoryService categoryService;
    private final IBrandService brandService;

    @GetMapping
    public String list(Model model) {
        List<ProductResponse> products = productService.getAllProducts();
        model.addAttribute("products", products);

        List<CategoryResponse> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);

        List<BrandResponse> brands = brandService.getAllBrands();
        model.addAttribute("brands", brands);
        return "views/admin/manage-data/manage-product";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ApiResponse<ProductResponse> getToUpdate(@PathVariable("id") String id) {
        return ApiResponse.<ProductResponse>builder()
                .code(200)
                .message("success")
                .data(productService.getProductById(id))
                .build();
    }

    @PostMapping
    @ResponseBody
    public ApiResponse<String> addProduct(@RequestBody ProductRequest request){
        productService.saveProduct(request);
        return ApiResponse.<String>builder()
                .code(200)
                .message("Thêm sản phẩm thành công")
                .data("success")
                .build();
    }

    @PutMapping
    @ResponseBody
    public ApiResponse<String> updateProduct(@RequestBody ProductRequest request){
        productService.saveProduct(request);
        return ApiResponse.<String>builder()
                .code(200)
                .message("Sửa sản phẩm thành công")
                .data("success")
                .build();
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ApiResponse<String> deleteProduct(@PathVariable String id){
        productService.deleteProduct(id);
        return ApiResponse.<String>builder()
                .code(200)
                .message("Xóa sản phẩm thành công")
                .data("success")
                .build();
    }
}
