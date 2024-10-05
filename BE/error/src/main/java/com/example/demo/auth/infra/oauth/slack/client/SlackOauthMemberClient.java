package com.example.demo.auth.infra.oauth.slack.client;

import com.example.demo.auth.application.model.OauthMemberModel;
import com.example.demo.auth.application.model.OauthServerType;
import com.example.demo.auth.application.model.client.OauthMemberClient;
import com.example.demo.auth.infra.oauth.slack.config.SlackOauthConfig;
import com.example.demo.auth.infra.oauth.slack.converter.OauthModelConverter;
import com.example.demo.auth.infra.oauth.slack.dto.SlackApiResponse;
import com.example.demo.auth.infra.oauth.slack.dto.SlackMember;
import com.example.demo.auth.infra.oauth.slack.dto.SlackToken;
import com.example.demo.auth.infra.oauth.slack.exception.SlackApiException;
import com.example.demo.auth.infra.oauth.slack.support.SlackFourQuery;
import com.example.demo.auth.infra.oauth.slack.support.SlackQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;



@Component
@RequiredArgsConstructor
public class SlackOauthMemberClient implements OauthMemberClient {
    private static final String TOKEN_METHOD_NAME = "getToken";
    private static final String MEMBER_INFO_METHOD_NAME = "getMemberInfo";
    private static final String BEARER = "Bearer";
    private static final Integer sum = 1;

    private final SlackOauthConfig oauthConfig;
    private final SlackOauthApiClient slackApiClient;
    private final OauthModelConverter oauthModelConverter;


    @Override
    public OauthServerType support() {
        return OauthServerType.SLACK;
    }

    @Override
    public OauthMemberModel fetch(String code, String uri) {

        SlackToken slackToken =
                execute(
                        slackApiClient::fetchToken,
                        TOKEN_METHOD_NAME,
                        oauthConfig.getClientId(),
                        code,
                        oauthConfig.getClientSecret(),
                        uri);

        SlackMember slackMember =
                execute(
                        slackApiClient::fetchMember,
                        MEMBER_INFO_METHOD_NAME,
                        requestToken(slackToken.getToken()));

        return oauthModelConverter.from(
                slackToken.getUserId(), slackMember.getName(), OauthServerType.SLACK);
    }

    private <T extends SlackApiResponse, K, U, R, S> T execute(
            final SlackFourQuery<T, K, U, R, S> slackFunction,
            final String methodName,
            final K param1,
            final U param2,
            final R param3,
            final S param4) {
        T result = slackFunction.execute(param1, param2, param3, param4);
        validateResponse(methodName, result);
        return result;
    }

    private <T extends SlackApiResponse, K> T execute(
            final SlackQuery<T, K> slackFunction, final String methodName, final K request) {
        T result = slackFunction.execute(request);
        validateResponse(methodName, result);
        return result;
    }

    private String requestToken(String token) {
        return String.format("%s %s", BEARER, token);
    }

    private <T extends SlackApiResponse> void validateResponse(String methodName, T response) {
        if (!response.isOk()) {
            System.out.println("validate Response: " + response.isOk());
            throw new SlackApiException(methodName, response);
        }
    }
}