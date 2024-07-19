package com.example.demo.auth.infra.oauth.slack.client;

import com.example.demo.auth.infra.oauth.slack.dto.SlackMember;
import com.example.demo.auth.infra.oauth.slack.dto.SlackToken;


public interface SlackOauthApiClient {
    SlackToken fetchToken(String client, String code, String clientSecret, String redirectUrl);

    SlackMember fetchMember(String token);
}
