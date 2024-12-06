package com.example.e_fashion.service;

import com.example.e_fashion.dto.request.LoginRequest;
import com.example.e_fashion.dto.request.RegistrationRequest;
import com.example.e_fashion.dto.response.AuthenticationResponse;
import com.example.e_fashion.dto.response.UserResponse;
import com.example.e_fashion.entity.User;
import jakarta.mail.MessagingException;

public interface IAuthenticateService {
    UserResponse register(RegistrationRequest request) throws MessagingException;

    AuthenticationResponse authenticate(LoginRequest request);

    void activationAccount(String emailToken) throws MessagingException;

    void sendValidationEmail(User user) throws MessagingException;

    String generateAndSaveEmailToken(User user);

    String generateActivationCode(int length);
}
