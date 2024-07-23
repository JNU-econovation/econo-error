package com.example.demo.auth.application.exception;


import org.springframework.http.HttpStatus;

public class TokenExpiredException extends AuthorizationException {

    private static final String FAIL_CODE = "4001";

    public TokenExpiredException() {
        super(FAIL_CODE, HttpStatus.FORBIDDEN);
    }

    @Override
    public String getMessage() {
        return "액세스 토큰이 만료되었습니다.";
    }
}
