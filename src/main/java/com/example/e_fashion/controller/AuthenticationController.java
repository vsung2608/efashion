package com.example.e_fashion.controller;

import com.example.e_fashion.dto.request.LoginRequest;
import com.example.e_fashion.dto.request.RegistrationRequest;
import com.example.e_fashion.dto.response.ApiResponse;
import com.example.e_fashion.dto.response.AuthenticationResponse;
import com.example.e_fashion.dto.response.LoginResponse;
import com.example.e_fashion.security.JwtService;
import com.example.e_fashion.service.impl.AuthenticateService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticateService authService;
    private final JwtService jwtService;

    @PostMapping("/register")
    @ResponseBody
    public ApiResponse<String> register(@RequestBody @Valid RegistrationRequest request) throws MessagingException {
        authService.register(request);
        return ApiResponse.<String>builder()
                .code(200)
                .message("Đăng ký tài khoản thành công")
                .build();
    }

    @PostMapping("/login")
    @ResponseBody
    private ApiResponse<LoginResponse> login(@RequestBody @Valid LoginRequest request, HttpServletResponse response) throws MessagingException {
        AuthenticationResponse authResponse = authService.authenticate(request);
        String jwtToken = authResponse.getToken();

        Cookie cookie = new Cookie("jwt", jwtToken);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(3600);

        response.addCookie(cookie);

        String url_redirect = null;
        if(authResponse.getAuthorities().stream().anyMatch("ROLE_ADMIN"::equals)){
            url_redirect = "/efashion/admin/dashboard";
        }else if(authResponse.getAuthorities().stream().anyMatch("ROLE_USER"::equals)){
            url_redirect = "/efashion/web/home-page";
        }
        return ApiResponse.<LoginResponse>builder()
                .code(200)
                .message("Đăng nhập thành công")
                .data(LoginResponse.builder().token(jwtToken).url(url_redirect).build())
                .build();
    }

    @PostMapping("/activate")
    @ResponseBody
    private ApiResponse<String> activation(@RequestBody String activationCode) throws MessagingException {
        authService.activationAccount(activationCode);

        return ApiResponse.<String>builder()
                .code(200)
                .message("Xác thực thành công")
                .build();
    }

    @GetMapping("/logout")
    private String logout(HttpServletResponse response){
        Cookie cookie = new Cookie("jwt", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);

        // Thêm cookie vào response
        response.addCookie(cookie);

        SecurityContextHolder.clearContext();
        return "redirect:/web/home-page";
    }
}
