package com.example.demo.auth.application.usecase;

public interface LogOutUsecase {
    void logOut(String token, final long memberId);
}
