package com.example.demo.auth.application.support;

import com.example.demo.auth.application.model.TokenModel;
import com.example.demo.auth.application.model.converter.TokenModelConverter;
import com.example.demo.auth.application.model.token.TokenProvider;
import com.example.demo.auth.application.model.token.TokenResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationTokenGenerator {
    private final TokenProvider tokenProvider;
    private final TokenModelConverter tokenModelConverter;
    private final TokenResolver tokenResolver;

    public TokenModel execute(final Long memberId) {
        String accessToken = tokenProvider.createAccessToken(memberId);
        String refreshToken = tokenProvider.createRefreshToken(memberId);

        return tokenModelConverter.from(
                accessToken,
                tokenResolver.getExpiredDateByAccessToken(accessToken),
                refreshToken,
                tokenResolver.getExpiredDateByRefreshToken(refreshToken));
    }
}
