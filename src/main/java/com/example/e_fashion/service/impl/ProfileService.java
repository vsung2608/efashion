package com.example.e_fashion.service.impl;

import com.example.e_fashion.dto.request.ProfileRequest;
import com.example.e_fashion.dto.response.ProfileResponse;
import com.example.e_fashion.entity.User;
import com.example.e_fashion.handler.BusinessError;
import com.example.e_fashion.handler.ResponseException;
import com.example.e_fashion.mapper.UserMapper;
import com.example.e_fashion.repository.UserRepository;
import com.example.e_fashion.service.IProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService implements IProfileService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ProfileResponse getProfile() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.isAuthenticated()) {
            var user = (User) authentication.getPrincipal();
            return userMapper.toProfileResponse(user);
        }
        return null;
    }

    @Override
    public void updateProfile(ProfileRequest request) {
        if(userRepository.existsByEmail(request.getEmail())){
            throw new ResponseException(BusinessError.EMAIL_EXISTED);
        }
        var user = userMapper.toUser(request);
        userRepository.updateUserById(user);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        var auth = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(passwordEncoder.matches(oldPassword, auth.getPassword())){
            userRepository.changePassword(auth.getId(), passwordEncoder.encode(newPassword));
        }else{
            throw new ResponseException(BusinessError.OLD_PASSWORD_INVALID);
        }
    }
}
