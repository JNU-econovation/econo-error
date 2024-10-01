package com.example.demo.schedule.application.model;


import com.example.demo.auth.application.support.Member;
import com.example.demo.auth.persistence.MemberEntity;
import com.example.demo.common.support.AbstractModel;
import com.example.demo.filter.application.model.FilterModel;
import com.example.demo.filter.persistence.FilterEntity;
import com.example.demo.schedule.persistence.ScheduleType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ScheduleModel implements AbstractModel {

    private Long eventId;
    private String eventName;
    private LocalDateTime eventStartDate;
    private LocalDateTime eventEndDate;
    private String eventInfo;
    private String eventPlace;
    private String scheduleType;
    private FilterEntity filter;
    private MemberEntity member;


    public ScheduleModel update(ScheduleModel requestModel) {

        eventName = requestModel.getEventName();
        eventStartDate = requestModel.getEventStartDate();
        eventEndDate = requestModel.getEventEndDate();
        eventInfo = requestModel.getEventInfo();
        eventPlace = requestModel.getEventPlace();

        return this;
    }


}
