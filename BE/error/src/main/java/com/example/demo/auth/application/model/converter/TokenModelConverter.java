package com.example.demo.auth.application.model.converter;

import com.example.demo.auth.application.model.TokenModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenModelConverter {
    public TokenModel from(
            String accessToken, Long accessExpiredTime, String refreshToken, Long refreshExpiredTime) {
        return TokenModel.builder()
                .accessToken(accessToken)
                .accessExpiredTime(accessExpiredTime)
                .refreshToken(refreshToken)
                .refreshExpiredTime(refreshExpiredTime)
                .build();
    }
}