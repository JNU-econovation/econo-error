package com.example.demo.schedule.application.dto;

import com.example.demo.common.support.dto.AbstractDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CreateScheduleRequest implements AbstractDto {

    private Long eventId;
    private String eventName;
    private LocalDateTime eventStartDate;
    private LocalDateTime eventEndDate;
    private String eventInfo;
    private String eventPlace;

}
