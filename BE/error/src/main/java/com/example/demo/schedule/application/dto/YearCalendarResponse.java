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
public class YearCalendarResponse {
    private Long eventId;
    private String eventStartDate;
    private String eventEndDate;
}
