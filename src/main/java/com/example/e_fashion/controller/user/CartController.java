package com.example.e_fashion.controller.user;

import com.example.e_fashion.dto.response.ApiResponse;
import com.example.e_fashion.dto.response.CartResponse;
import com.example.e_fashion.entity.Order;
import com.example.e_fashion.service.IAuthenticateService;
import com.example.e_fashion.service.ICartService;
import com.example.e_fashion.service.IOrderService;
import com.example.e_fashion.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/web/carts")
@RequiredArgsConstructor
public class CartController {

    private final ICartService cartService;
    private final IOrderService orderService;
    private final IAuthenticateService authenticateService;

    @GetMapping
    public String displayCart(Authentication authentication, Model model) {
        if(authentication == null)model.addAttribute("logined", false);
        else model.addAttribute("logined", true);

        assert authentication != null;
        var user = (UserDetails) authentication.getPrincipal();
        var userId = authenticateService.findIdByEmail(user.getUsername());

        List<CartResponse> carts = cartService.getAllItemByUserId(userId);
        model.addAttribute("carts", carts);

        BigDecimal total = BigDecimal.ZERO;
        for (var c : carts) total = total.add(c.getProduct().getPromotionalPrice());
        model.addAttribute("total", total);
        return "views/user/carts";
    }

    @PostMapping
    @ResponseBody
    public ApiResponse<String> payCart(Authentication authentication){

        assert authentication != null;
        var user = (UserDetails) authentication.getPrincipal();
        var u = authenticateService.findByEmail(user.getUsername());

        Order order = cartService.payCart(u);
        orderService.saveOrder(order);

        cartService.deleteAllByUserId(u.getId());

        return ApiResponse.<String>builder()
                .code(200)
                .message("Đã gửi yêu cầu thanh toán sản phẩm trong giỏ. Vui lòng chờ xác nhận")
                .build();
    }

    @PostMapping("/add-item")
    @ResponseBody
    public ApiResponse<String> addCartItem(@RequestParam String id, @RequestParam int quantity, Authentication authentication){
        var user = (UserDetails) authentication.getPrincipal();
        var email = user.getUsername();

        cartService.addProductInCart(email, id, quantity);

        return ApiResponse.<String>builder()
                .code(200)
                .message("Đã gửi yêu cầu thanh toán sản phẩm trong giỏ. Vui lòng chờ xác nhận")
                .build();
    }

    @DeleteMapping
    @ResponseBody
    public ApiResponse<String> deleteCart(Authentication authentication) {
        assert authentication != null;
        var user = (UserDetails) authentication.getPrincipal();
        var userId = authenticateService.findIdByEmail(user.getUsername());

        cartService.deleteAllByUserId(userId);

        return ApiResponse.<String>builder()
                .code(200)
                .message("Đã xóa tất cả sản phẩm trong giỏ")
                .build();
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ApiResponse<String> deleteItem(@PathVariable String id) {
        cartService.deleteByCartId(id);

        return ApiResponse.<String>builder()
                .code(200)
                .message("Đã xóa tất cả sản phẩm trong giỏ")
                .build();
    }
}
