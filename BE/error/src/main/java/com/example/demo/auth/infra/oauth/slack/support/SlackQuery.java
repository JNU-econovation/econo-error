package com.example.demo.auth.infra.oauth.slack.support;

import com.example.demo.auth.infra.oauth.slack.dto.SlackApiResponse;

@FunctionalInterface
public interface SlackQuery<T extends SlackApiResponse, K> {
    T execute(K request);
}

