package com.example.e_fashion.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class AuthenticationResponse {
    String token;
    List<String> authorities;
}
