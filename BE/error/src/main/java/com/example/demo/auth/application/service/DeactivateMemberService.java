package com.example.demo.auth.application.service;

import com.example.demo.auth.application.event.DeletedMemberEvent;
import com.example.demo.auth.application.model.token.TokenResolver;
import com.example.demo.auth.application.usecase.LogOutUsecase;
import com.example.demo.auth.application.usecase.WithDrawUsecase;
import com.example.demo.auth.persistence.AuthenticationRepository;
import com.example.demo.auth.persistence.MemberRepository;
import com.example.demo.auth.persistence.OAuthMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DeactivateMemberService implements LogOutUsecase, WithDrawUsecase {

    private final TokenResolver tokenResolver;
    private final AuthenticationRepository authenticationRepository;
    private final MemberRepository memberRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final OAuthMemberRepository oAuthMemberRepository;

    @Override
    public void logOut(String token, long memberId) {
        saveUsedToken(token, memberId);
    }

    private void saveUsedToken(final String token, final Long memberId) {
        authenticationRepository.save(token, memberId, getExpiredToken(token));
    }

    private Long getExpiredToken(final String token) {
        return tokenResolver.getExpiredDateByRefreshToken(token);
    }


    @Override
    @Transactional
    public void withDraw(String token, Long memberId) {
        if (isNotMember(memberId)) {
            return;
        }
        deleteMemberData(memberId);
        eventPublisher.publishEvent(DeletedMemberEvent.of(memberId, token));
    }

    private boolean isNotMember(Long memberId) {
        return memberRepository.findById(memberId).isEmpty();
    }

    private void deleteMemberData(Long memberId) {
        memberRepository.deleteById(memberId);
        oAuthMemberRepository.findByMemberId(memberId).ifPresent(oAuthMemberRepository::delete);
    }

}
