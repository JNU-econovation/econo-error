package com.example.demo.auth.application.service;

import com.example.demo.auth.application.model.OauthMemberModel;
import com.example.demo.auth.application.model.client.OauthMemberClientComposite;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OauthClientService {
    private final OauthMemberClientComposite oauthMemberClientComposite;

    public OauthMemberModel getOauthMember(String oauthServerType, String authCode, String uri) {
        OauthMemberModel model = oauthMemberClientComposite.fetch(oauthServerType, authCode, uri);
        model.validateNameFormat();

        return model;
    }
}
