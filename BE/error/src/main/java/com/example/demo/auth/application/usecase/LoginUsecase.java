package com.example.demo.auth.application.usecase;

import com.example.demo.auth.application.model.TokenModel;

public interface LoginUsecase {
    TokenModel login(String oauthServerType, String authCode, String uri);
}