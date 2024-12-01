package com.example.demo.schedule.domain.model;


import com.example.demo.auth.persistence.MemberEntity;
import com.example.demo.auth.persistence.MemberModel;
import com.example.demo.common.support.AbstractModel;
import com.example.demo.filter.application.model.FilterModel;
import com.example.demo.filter.persistence.FilterEntity;
import com.example.demo.filter.persistence.FilterRepository;
import com.example.demo.schedule.domain.ScheduleRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@ToString
@AllArgsConstructor
@Builder(toBuilder = true)
@RequiredArgsConstructor
public class ScheduleModel implements AbstractModel {

    private Long eventId;
    private String eventName;
    private LocalDateTime eventStartDate;
    private LocalDateTime eventEndDate;
    private String eventInfo;
    private String eventPlace;
    private String scheduleType;
    private FilterEntity filter;
    private FilterModel filterModel;
    private MemberModel memberModel;
    private Long filterId;
    private Long memberId;
    private FilterRepository filterRepository;
    private ScheduleRepository scheduleRepository;

    public ScheduleModel update(ScheduleModel requestModel) {

        eventName = requestModel.getEventName();
        eventStartDate = requestModel.getEventStartDate();
        eventEndDate = requestModel.getEventEndDate();
        eventInfo = requestModel.getEventInfo();
        eventPlace = requestModel.getEventPlace();

        return this;
    }

    public String getFilterColor() {
        return scheduleRepository.findFilterColor(this.filterId);
    }
}
