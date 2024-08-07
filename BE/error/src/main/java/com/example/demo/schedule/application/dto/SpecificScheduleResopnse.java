package com.example.demo.schedule.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class SpecificScheduleResopnse {
    private Long eventId;
    private String eventName;
    private String eventInfo;
    private String eventStartDate;
    private String eventEndDate;
    private String eventPlace;
    private String filterColor;
}
