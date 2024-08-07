package com.example.demo.auth.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OAuthMemberRepository extends JpaRepository<OAuthMemberEntity, Long> {
    @Query("SELECT o FROM OAuthMemberEntity  o WHERE o.oauthId=:oauthId")
    Optional<OAuthMemberEntity> findByOauthId(@Param("oauthId") String oauthId);

    @Query("SELECT o FROM OAuthMemberEntity  o WHERE o.memberId=:memberId")
    Optional<OAuthMemberEntity> findByMemberId(@Param("memberId") Long memberId);
}
