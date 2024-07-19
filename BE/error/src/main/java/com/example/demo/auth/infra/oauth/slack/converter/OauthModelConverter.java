package com.example.demo.auth.infra.oauth.slack.converter;

import com.example.demo.auth.application.model.OauthMemberModel;
import com.example.demo.auth.application.model.OauthServerType;
import org.springframework.stereotype.Component;

@Component
public class OauthModelConverter {
    public OauthMemberModel from(
            final String oauthId, final String name, final OauthServerType type) {
        return OauthMemberModel.builder().oauthId(oauthId).name(name).oauthServerType(type).build();
    }
}
