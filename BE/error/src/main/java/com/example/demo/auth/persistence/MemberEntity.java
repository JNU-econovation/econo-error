package com.example.demo.auth.persistence;

import com.example.demo.auth.application.model.OauthServerType;
import com.example.demo.common.persistence.BaseEntity;
import com.example.demo.filter.persistence.FilterEntity;
import jakarta.persistence.*;
import lombok.*;

import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@SuperBuilder(toBuilder = true)
@Entity
@Table(
        name = MemberEntity.ENTITY_PREFIX,
        indexes = {
                @Index(name = "idx_member_name", columnList = "member_name"),
                //@Index(name = "idx_member_active_status", columnList = "member_active_status")
        })
@EntityListeners(AuditingEntityListener.class)
public class MemberEntity extends BaseEntity {

    public static final String ENTITY_PREFIX = "member";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ENTITY_PREFIX + "_id", nullable = false)
    private Long id;

    @Column(name = ENTITY_PREFIX + "_name", nullable = false)
    private String name;

    @Column(name = ENTITY_PREFIX + "_oath_server_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private OauthServerType oauthServerType;
}
