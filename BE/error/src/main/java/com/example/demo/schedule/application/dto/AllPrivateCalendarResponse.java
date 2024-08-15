package com.example.demo.schedule.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class AllPrivateCalendarResponse {
    private Long eventId;
    private String eventName;
    private String eventStartDate;
    private String eventEndDate;
    private String eventPlace;
    private String eventInfo;
    private String eventScope;
    private String scheduleType;
    private Long filterId;
    private String filterName;
    private String filterColor;
}
