package com.example.demo.auth.application.model.converter;

import com.example.demo.auth.application.dto.TokenResponse;
import org.springframework.stereotype.Component;

@Component
public class TokenResponseConverter {
    public TokenResponse from(String accessToken, Long accessExpiredTime) {
        return TokenResponse.builder()
                .accessToken(accessToken)
                .accessExpiredTime(accessExpiredTime)
                .build();
    }
}
