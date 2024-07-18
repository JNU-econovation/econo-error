package com.example.demo.auth.application.usecase;


import com.example.demo.auth.application.model.TokenModel;

public interface ReissueUsecase {
    TokenModel execute(final String token);
}
