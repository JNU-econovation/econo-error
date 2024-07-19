package com.example.demo.auth.infra.oauth.slack.support;

import com.example.demo.auth.infra.oauth.slack.dto.SlackApiResponse;

@FunctionalInterface
public interface SlackFourQuery<T extends SlackApiResponse, K, U, V, S> {
    T execute(K k, U u, V v, S s);
}