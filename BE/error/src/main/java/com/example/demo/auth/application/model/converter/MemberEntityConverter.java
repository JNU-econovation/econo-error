package com.example.demo.auth.application.model.converter;

import com.example.demo.auth.application.model.MemberModel;
import com.example.demo.auth.application.model.OauthServerType;
import com.example.demo.auth.persistence.MemberEntity;
import com.example.demo.common.support.converter.AbstractEntityConverter;
import org.springframework.stereotype.Component;

@Component
public class MemberEntityConverter implements AbstractEntityConverter<MemberEntity, MemberModel> {

    @Override
    public MemberModel from(MemberEntity source) {
        return MemberModel.builder()
                .id(source.getId())
                .name(source.getName())
                .oauthServerType(source.getOauthServerType())
                .build();
    }

    @Override
    public MemberEntity toEntity(MemberModel source) {
        return MemberEntity.builder()
                .id(source.getId())
                .name(source.getName())
                //.activeStatus(ActiveStatus.find(source.getActiveStatus()))
                .oauthServerType(source.getOauthServerType())
                .build();
    }

    public MemberEntity toEntity(String name, OauthServerType oauthServerType) {
        return MemberEntity.builder().name(name).oauthServerType(oauthServerType).build();
    }
}
