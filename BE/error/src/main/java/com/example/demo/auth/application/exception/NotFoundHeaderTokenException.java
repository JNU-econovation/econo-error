package com.example.demo.auth.application.exception;

import org.springframework.http.HttpStatus;

/** 토큰이 헤더에 존재하지 않을 때 발생하는 예외 */
public class NotFoundHeaderTokenException extends AuthorizationException {
    private static final String FAIL_CODE = "4000";

    public NotFoundHeaderTokenException() {
        super(FAIL_CODE, HttpStatus.UNAUTHORIZED);
    }

    @Override
    public String getMessage() {
        return "엑세스 토큰이 존재하지 않습니다.";
    }
}

