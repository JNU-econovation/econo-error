package com.example.demo.auth.application.model;

import com.example.demo.auth.application.exception.NotFoundOauthServerException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum OauthServerType {
    SLACK("slack");

    private final String oauthServer;

    OauthServerType(String oauthServer) {
        this.oauthServer = oauthServer;
    }

    public static OauthServerType find(String type) {
        return Arrays.stream(values())
                .filter(oauthServerType -> oauthServerType.getOauthServer().equals(type))
                .findAny()
                .orElseThrow(() -> new NotFoundOauthServerException(type));
    }
}

