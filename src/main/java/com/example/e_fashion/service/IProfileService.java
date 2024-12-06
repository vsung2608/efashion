package com.example.e_fashion.service;

import com.example.e_fashion.dto.request.ProfileRequest;
import com.example.e_fashion.dto.response.ProfileResponse;

public interface IProfileService {

    ProfileResponse getProfile();

    void updateProfile(ProfileRequest request);

    void changePassword(String oldPassword, String newPassword);
}
