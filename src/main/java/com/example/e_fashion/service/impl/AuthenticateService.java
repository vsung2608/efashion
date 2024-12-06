package com.example.e_fashion.service.impl;


import com.example.e_fashion.dto.request.LoginRequest;
import com.example.e_fashion.dto.request.RegistrationRequest;
import com.example.e_fashion.dto.response.AuthenticationResponse;
import com.example.e_fashion.dto.response.UserResponse;
import com.example.e_fashion.email.EmailService;
import com.example.e_fashion.email.EmailTemplateName;
import com.example.e_fashion.entity.EmailToken;
import com.example.e_fashion.entity.Role;
import com.example.e_fashion.entity.User;
import com.example.e_fashion.handler.BusinessError;
import com.example.e_fashion.handler.ResponseException;
import com.example.e_fashion.mapper.UserMapper;
import com.example.e_fashion.repository.EmailTokenRepository;
import com.example.e_fashion.repository.RoleRepository;
import com.example.e_fashion.repository.UserRepository;
import com.example.e_fashion.security.JwtService;
import com.example.e_fashion.service.IAuthenticateService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticateService implements IAuthenticateService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final EmailTokenRepository emailTokenRepository;
    private final EmailService emailService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Value("${application.emailing.frontend.activation-url}")
    private String activationUrl;

    public UserResponse register(RegistrationRequest request) throws MessagingException {
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleRepository.findByName("ADMIN")
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRoles(new ArrayList<>(Collections.singleton(role)));

        try {
            userRepository.save(user);
        }catch (Exception e){
            throw new ResponseException(BusinessError.EMAIL_EXISTED);
        }
        sendValidationEmail(user);
        return userMapper.toUserResponse(user);
    }

    public AuthenticationResponse authenticate(LoginRequest request){
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword())
        );

        var user = (User) auth.getPrincipal();
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("fullname", user.fullname());
        List<String> roles = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        var token = jwtService.generateToken(claims, user);
        return AuthenticationResponse.builder()
                .token(token)
                .authorities(roles)
                .build();
    }

    public void activationAccount(String emailToken) throws MessagingException {
        var emailTokenSaved = emailTokenRepository.findByToken(emailToken)
                .orElseThrow(() -> new RuntimeException("Email token not found"));

        if(LocalDateTime.now().isAfter(emailTokenSaved.getExpiresAt())){
            sendValidationEmail(emailTokenSaved.getUser());
            throw new RuntimeException("Email token expired. A new email token has been created, please check your email.");
        }

        var user = userRepository.findById(emailTokenSaved.getUser().getId())
                        .orElseThrow(() -> new RuntimeException("User not found"));

        user.setEnabled(true);
        userRepository.save(user);
        emailTokenSaved.setValidatedAt(LocalDateTime.now());
        emailTokenRepository.save(emailTokenSaved);
    }

    public void sendValidationEmail(User user) throws MessagingException {
        var activationCode = generateAndSaveEmailToken(user);

        emailService.sendEmail(
                user.getEmail(),
                user.getName(),
                "Activate_account",
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activationCode,
                activationUrl
        );
    }

    public String generateAndSaveEmailToken(User user){
        var activationCode = generateActivationCode(6);

        var emailToken = EmailToken.builder()
                .token("123456")
                .createdAt(LocalDateTime.from(LocalDateTime.now()))
                .expiresAt(LocalDateTime.from(LocalDateTime.now().plusMinutes(15)))
                .user(user)
                .build();

        emailTokenRepository.save(emailToken);
        return emailToken.getToken();
    }

    public String generateActivationCode(int length){
        String characters = "0123456789";
        StringBuilder code = new StringBuilder();
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < length; i++) {
            int charAt = random.nextInt(characters.length());
            code.append(characters.charAt(charAt));
        }
        return code.toString();
    }
}
