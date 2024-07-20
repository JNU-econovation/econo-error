package com.example.demo.auth.application.service;


import com.example.demo.auth.application.exception.InvalidTokenException;
import com.example.demo.auth.application.model.TokenModel;
import com.example.demo.auth.application.model.token.TokenResolver;
import com.example.demo.auth.application.support.AuthenticationTokenGenerator;
import com.example.demo.auth.application.usecase.ReissueUsecase;
import com.example.demo.auth.persistence.AuthenticationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReissueService implements ReissueUsecase {

    private final TokenResolver tokenResolver;
    private final AuthenticationTokenGenerator authenticationTokenGenerator;
    private final AuthenticationRepository authenticationRepository;

    @Transactional
    @Override
    public TokenModel execute(final String token) {

        Long memberId = tokenResolver.getUserDataByRefreshToken(token);

        validateToken(token);
        saveUsedToken(token, memberId);

        return authenticationTokenGenerator.execute(memberId);
    }

    private void validateToken(final String token) {
        boolean isExistToken = authenticationRepository.isExistToken(token);

        if (isExistToken) {
            throw new InvalidTokenException();
        }
    }

    private void saveUsedToken(final String token, final Long memberId) {
        authenticationRepository.save(token, memberId, getExpiredToken(token));
    }

    private Long getExpiredToken(final String token) {
        return tokenResolver.getExpiredDateByRefreshToken(token);
    }
}
