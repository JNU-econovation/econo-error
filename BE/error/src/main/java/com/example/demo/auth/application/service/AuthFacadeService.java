package com.example.demo.auth.application.service;

import com.example.demo.auth.application.model.OauthMemberModel;
import com.example.demo.auth.application.model.TokenModel;
import com.example.demo.auth.application.support.AuthenticationTokenGenerator;
import com.example.demo.auth.application.usecase.LoginUsecase;
import com.example.demo.auth.persistence.OAuthMemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthFacadeService implements LoginUsecase {
    private final OauthClientService oauthClientService;
    private final AuthService authService;
    private final AuthenticationTokenGenerator authenticationTokenGenerator;

    @Override
    public TokenModel login(String oauthServerType, String authCode, String uri) {
        OauthMemberModel model = oauthClientService.getOauthMember(oauthServerType, authCode, uri);
        OAuthMemberEntity entity = authService.authenticate(model);
        return authenticationTokenGenerator.execute(entity.getMemberId());
    }
}
