package com.example.e_fashion.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private String id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private String phone;

    private String address;

    private String city;

    private String state;

    private LocalDate dob;

    private String gender;

    private boolean accountLocked;

    private boolean enabled;
}
