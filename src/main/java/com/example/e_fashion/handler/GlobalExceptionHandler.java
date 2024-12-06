package com.example.e_fashion.handler;

import com.example.e_fashion.dto.response.ApiResponse;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ResponseException.class)
    public ResponseEntity<ApiResponse<String>> responseExceptionHandler(ResponseException ex){
        return ResponseEntity.status(ex.getError().getStatus())
                .body(ApiResponse.<String>builder()
                        .code(ex.getError().getCode())
                        .message(ex.getError().getMessage())
                        .data(null)
                        .build());
    }

//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<ApiResponse<String>> runtimeExceptionHandler(RuntimeException ex){
//        return ResponseEntity.badRequest()
//                .body(ApiResponse.<String>builder()
//                        .code(999)
//                        .message(ex.getMessage())
//                        .data(null)
//                        .build());
//    }

    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<ApiResponse<String>> messagingExceptionHandler(MessagingException ex){
        return ResponseEntity.badRequest()
                .body(ApiResponse.<String>builder()
                        .code(998)
                        .message(ex.getMessage())
                        .data(null)
                        .build());
    }
}
