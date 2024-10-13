package com.example.demo.filter.persistence;

import com.example.demo.auth.persistence.MemberEntity;
import com.example.demo.common.persistence.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
@SuperBuilder(toBuilder = true)
@Table(name = "filter")
@EntityListeners(AuditingEntityListener.class)
public class FilterEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long filterId;

    @Column(nullable = false)
    private String filterName;

    @Column(nullable = false)
    private String filterColor;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity member;
}
