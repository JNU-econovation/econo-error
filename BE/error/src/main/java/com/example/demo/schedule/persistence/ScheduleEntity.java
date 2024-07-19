package com.example.demo.schedule.persistence;

import com.example.demo.common.persistence.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
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

    //@Enumerated(EnumType.STRING)
    //@Column(nullable = false)
    //private ScheduleType scheduleTypeType;

}
