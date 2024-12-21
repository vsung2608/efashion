package com.example.e_fashion.controller.user;

import com.example.e_fashion.dto.response.ApiResponse;
import com.example.e_fashion.dto.response.BrandResponse;
import com.example.e_fashion.dto.response.CategoryResponse;
import com.example.e_fashion.dto.response.ProductResponse;
import com.example.e_fashion.service.IBrandService;
import com.example.e_fashion.service.ICategoryService;
import com.example.e_fashion.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/web/products")
@RequiredArgsConstructor
public class ProductsController {
    private final IProductService productService;
    private final ICategoryService categoryService;
    private final IBrandService brandService;

    @GetMapping
    public String products(Authentication authentication, Model model){
        if(authentication == null)model.addAttribute("logined", false);
        else model.addAttribute("logined", true);

        List<ProductResponse> products = productService.getAllProducts();
        model.addAttribute("products", products);

        List<CategoryResponse> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);

        List<BrandResponse> brands = brandService.getAllBrands();
        model.addAttribute("brands", brands);
        return "views/user/products";
    }

    @GetMapping("/{keyword}")
    public String productsByKeyword(@PathVariable String keyword, Authentication authentication, Model model){
        if(authentication == null)model.addAttribute("logined", false);
        else model.addAttribute("logined", true);

        List<ProductResponse> products = productService.getAllByKeyword(keyword);
        model.addAttribute("products", products);

        List<CategoryResponse> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);

        List<BrandResponse> brands = brandService.getAllBrands();
        model.addAttribute("categories", brands);
        return "views/user/products";
    }

    @GetMapping("/detail/{id}")
    public String productDetail(@PathVariable String id, Authentication authentication, Model model){
        if(authentication == null)model.addAttribute("logined", false);
        else model.addAttribute("logined", true);

        ProductResponse product = productService.getProductById(id);
        model.addAttribute("product", product);

        List<ProductResponse> list = productService.getProductByBrandId(product.getBrand().getBrandId());
        model.addAttribute("products", list);

        return "views/user/detail-product";
    }

}
