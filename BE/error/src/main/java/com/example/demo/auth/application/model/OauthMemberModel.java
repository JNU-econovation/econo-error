package com.example.demo.auth.application.model;

import com.example.demo.auth.application.exception.InvalidNameFormatException;
import com.example.demo.auth.application.support.ValidatorNameFormat;
import com.example.demo.common.support.AbstractModel;
import lombok.*;


@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class OauthMemberModel implements AbstractModel {
    private String oauthId;
    private String name;
    private OauthServerType oauthServerType;

    public void validateNameFormat() {
        if (!ValidatorNameFormat.isSatisfy(name)) {
            throw new InvalidNameFormatException(name);
        }
    }
}