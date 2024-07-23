package com.example.demo.schedule.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class AllCalendarResponse {
    private Long eventId;
    private String eventName;
    private String eventStartDate;
    private String eventEndDate;
}
