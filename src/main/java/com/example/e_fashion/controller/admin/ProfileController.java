package com.example.e_fashion.controller.admin;

import com.example.e_fashion.dto.request.ProfileRequest;
import com.example.e_fashion.dto.response.ApiResponse;
import com.example.e_fashion.service.IProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class ProfileController {
    private final IProfileService profileService;

    @GetMapping("/get-profile")
    public String getMyProfile(Model model){
        var res = profileService.getProfile();
        model.addAttribute("profile", res);
        model.addAttribute("position", "Web developer");
        return "views/admin/profile";
    }

    @PutMapping("/update-profile")
    @ResponseBody
    public ApiResponse<String> updateProfile(@RequestBody ProfileRequest profile){
        profileService.updateProfile(profile);
        return ApiResponse.<String>builder()
                .code(200)
                .message("Cập nhật hồ sơ thành công!")
                .data("success")
                .build();
    }

    @PutMapping("/change-password")
    @ResponseBody
    public ApiResponse<String> changePassword(@RequestBody Map<String, Object> payload){
        String oldPassword = (String) payload.get("oldPassword");
        String newPassword = (String) payload.get("newPassword");
        profileService.changePassword(oldPassword, newPassword);
        return ApiResponse.<String>builder()
                .code(200)
                .message("Cập nhật mật khảu thành công")
                .data("success")
                .build();
    }
}
