package com.example.demo.auth.application.service;

import com.example.demo.auth.application.model.OauthMemberModel;
import com.example.demo.auth.application.model.converter.MemberEntityConverter;
import com.example.demo.auth.application.model.converter.OauthMemberEntityConverter;
import com.example.demo.auth.persistence.MemberEntity;
import com.example.demo.auth.persistence.MemberRepository;
import com.example.demo.auth.persistence.OAuthMemberEntity;
import com.example.demo.auth.persistence.OAuthMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {
    private final MemberRepository memberRepository;
    private final OAuthMemberRepository oAuthMemberRepository;
    private final MemberEntityConverter memberEntityConverter;
    private final OauthMemberEntityConverter oauthMemberEntityConverter;

    @Transactional
    public OAuthMemberEntity authenticate(final OauthMemberModel model) {
        return oAuthMemberRepository
                .findByOauthId(model.getOauthId())
                .orElseGet(() -> signUpMember(model));
    }

    private OAuthMemberEntity signUpMember(final OauthMemberModel model) {
        MemberEntity entity = memberEntityConverter.toEntity(model.getName(), model.getOauthServerType());
        MemberEntity savedMember = memberRepository.save(entity);
        return saveOauthInfoEntity(model.getOauthId(), savedMember.getId());
    }

    private OAuthMemberEntity saveOauthInfoEntity(final String oauthId, final Long memberId) {
        OAuthMemberEntity entity = oauthMemberEntityConverter.toEntity(oauthId, memberId);
        return oAuthMemberRepository.save(entity);
    }
}
