package com.example.e_fashion.handler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BusinessError {
    EMAIL_EXISTED(1001, "Email đã tồn tại. Vui lòng chọn email khác!", HttpStatus.BAD_REQUEST),
    OLD_PASSWORD_INVALID(1002, "Mật khẩu cũ không hợp lệ. Vui lòng kiểm tra lại!", HttpStatus.BAD_REQUEST),
    ;

    private final int code;
    private final String message;
    private final HttpStatus status;

    BusinessError(int code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
