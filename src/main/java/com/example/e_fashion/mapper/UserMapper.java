package com.example.e_fashion.mapper;

import com.example.e_fashion.dto.request.LoginRequest;
import com.example.e_fashion.dto.request.ProfileRequest;
import com.example.e_fashion.dto.request.RegistrationRequest;
import com.example.e_fashion.dto.response.ProfileResponse;
import com.example.e_fashion.dto.response.UserResponse;
import com.example.e_fashion.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(LoginRequest request);
    User toUser(RegistrationRequest user);
    User toUser(ProfileRequest request);
    UserResponse toUserResponse(User user);
    ProfileResponse toProfileResponse(User user);
}
