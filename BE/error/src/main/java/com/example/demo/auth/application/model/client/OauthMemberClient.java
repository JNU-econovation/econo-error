package com.example.demo.auth.application.model.client;

import com.example.demo.auth.application.model.OauthMemberModel;
import com.example.demo.auth.application.model.OauthServerType;

public interface OauthMemberClient {
    OauthServerType support();

    OauthMemberModel fetch(String code, String uri);
}
