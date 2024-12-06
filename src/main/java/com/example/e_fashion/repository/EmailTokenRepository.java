package com.example.e_fashion.repository;

import com.example.e_fashion.entity.EmailToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailTokenRepository extends JpaRepository<EmailToken, String> {

    Optional<EmailToken> findByToken(String token);
}
