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
@SuperBuilder(toBuilder = true) // TODO what is the superbuilder??
@Table(name = "Schedule")
//@EntityListeners(AuditingEntityListener.class)
public class ScheduleEntity extends BaseEntity { // ScheduleEntity에서 BaseEntity를 extends하지 않으면 왜 ScheduleEntityConverter 여기서 에러가 발생했는지 애하가 잘 안가네

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
