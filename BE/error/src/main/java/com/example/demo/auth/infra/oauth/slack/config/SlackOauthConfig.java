package com.example.demo.auth.infra.oauth.slack.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:/application.properties")
@Configuration
@Setter
@Getter
public class SlackOauthConfig {

    @Value("${spring.config.activate.on-profile.oauth.provider.slack.clientId}")
    private String clientId;

    @Value("${spring.config.activate.on-profile.oauth.provider.slack.cliientSecret}")
    private String clientSecret;
}
