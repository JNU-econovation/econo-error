package com.example.demo.schedule.persistence;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Entity
public class ScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ScheduleType scheduleTypeType;

    @Builder
    public ScheduleEntity(Long eventId, String eventName, LocalDateTime eventStartDate, LocalDateTime eventEndDate, String eventInfo, String eventPlace, ScheduleType eventType) {
        //this.eventId = eventId;
        this.eventName = eventName;
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
        this.eventInfo = eventInfo;
        this.eventPlace = eventPlace;
        this.scheduleTypeType = eventType;
    }
}
