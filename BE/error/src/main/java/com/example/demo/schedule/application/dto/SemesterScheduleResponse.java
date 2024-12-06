package com.example.demo.schedule.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SemesterScheduleResponse {
    private String eventName;
    private String eventStartDate;
    private String eventEndDate;
}
