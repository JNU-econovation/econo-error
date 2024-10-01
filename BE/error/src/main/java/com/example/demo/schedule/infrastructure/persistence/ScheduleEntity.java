package com.example.demo.schedule.infrastructure;

import com.example.demo.auth.persistence.MemberEntity;
import com.example.demo.common.persistence.BaseEntity;
import com.example.demo.filter.application.model.FilterModel;
import com.example.demo.filter.persistence.FilterEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
@SuperBuilder(toBuilder = true)
@Table(name = "Schedule")
@EntityListeners(AuditingEntityListener.class)
public class ScheduleEntity extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long eventId;

    @Column(nullable = false)
    private String eventName;

    @Column(nullable = false)
    private LocalDateTime eventStartDate;

    private LocalDateTime eventEndDate;

    @Column(nullable = false)
    private String eventInfo;

    @Column(nullable = false)
    private String eventPlace;

    @Column(nullable = false)
    private String scheduleType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "filter_id")
    private FilterEntity filter;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity member;
}
