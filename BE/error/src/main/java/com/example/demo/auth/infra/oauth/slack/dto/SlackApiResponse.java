package com.example.demo.auth.infra.oauth.slack.dto;

public interface SlackApiResponse {
    boolean isOk();

    String getError();
}
