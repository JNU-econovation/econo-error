package com.example.demo.schedule.application.dto;

import com.example.demo.auth.persistence.MemberEntity;
import com.example.demo.common.support.dto.AbstractDto;
import com.example.demo.filter.application.model.FilterModel;
import com.example.demo.filter.persistence.FilterEntity;
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
    private String scheduleType;
    private FilterEntity filter;
    private MemberEntity member;

}
