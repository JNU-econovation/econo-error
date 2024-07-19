package com.example.demo.auth.persistence;

import com.example.demo.common.persistence.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@SuperBuilder(toBuilder = true)
@Entity
@Table(name = OAuthMemberEntity.ENTITY_PREFIX)
public class OAuthMemberEntity extends BaseEntity {
    public static final String ENTITY_PREFIX = "oauth_member";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ENTITY_PREFIX + "_id", nullable = false)
    private Long id;

    @Column(name = ENTITY_PREFIX + "_oauth_id", nullable = false)
    private String oauthId;

    @Column(name = ENTITY_PREFIX + "_member_id", nullable = false)
    private Long memberId;
}
