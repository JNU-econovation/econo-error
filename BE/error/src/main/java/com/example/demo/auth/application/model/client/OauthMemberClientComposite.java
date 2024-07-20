package com.example.demo.auth.application.model.client;

import com.example.demo.auth.application.model.OauthMemberModel;
import com.example.demo.auth.application.model.OauthServerType;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class OauthMemberClientComposite {

    private final Map<OauthServerType, OauthMemberClient> clients;

    public OauthMemberClientComposite(Set<OauthMemberClient> providers) {
        this.clients =
                providers.stream()
                        .collect(Collectors.toMap(OauthMemberClient::support, Function.identity()));
    }

    public OauthMemberModel fetch(String oauthServerType, String authCode, String uri) {
        return getClient(oauthServerType).fetch(authCode, uri);
    }

    private OauthMemberClient getClient(String type) {
        OauthServerType oauthServerType = OauthServerType.find(type);
        return clients.get(oauthServerType);
    }
}
