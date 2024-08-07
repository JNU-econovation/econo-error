package com.example.demo.auth.infra.oauth.slack.client;

import com.example.demo.auth.infra.oauth.slack.dto.SlackMember;
import com.example.demo.auth.infra.oauth.slack.dto.SlackToken;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "SlackOpenFeign", url = "https://slack.com/api")
public interface SlackOauthApiClientImpl extends SlackOauthApiClient {
    @GetMapping(path = "/oauth.v2.access")
    SlackToken fetchToken(
            @RequestParam("client_id") String clientId,
            @RequestParam("code") String code,
            @RequestParam("client_secret") String clientSecret,
            @RequestParam("redirect_uri") String uri);


    @GetMapping(path = "/users.profile.get")
    SlackMember fetchMember(@RequestHeader("Authorization") String token);
}

