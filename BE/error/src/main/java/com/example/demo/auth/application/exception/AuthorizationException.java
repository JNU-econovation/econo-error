package com.example.demo.auth.application.exception;

import com.example.demo.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class AuthorizationException extends BusinessException {
    public AuthorizationException(String code, HttpStatus httpStatus) {
        super(code, httpStatus);
    }
}
