package com.example.demo.schedule.domain.model.converter;

import com.example.demo.common.support.converter.AbstractDtoConverter;
import com.example.demo.schedule.application.dto.CreateScheduleRequest;
import com.example.demo.schedule.application.dto.UpdateScheduleRequest;
import com.example.demo.schedule.domain.model.ScheduleModel;
import org.springframework.stereotype.Component;

@Component
public class ScheduleRequestConverter implements AbstractDtoConverter<CreateScheduleRequest, ScheduleModel> {

    @Override
    public ScheduleModel from(CreateScheduleRequest source) {
        return ScheduleModel.builder()
                .eventName(source.getEventName())
                .eventStartDate(source.getEventStartDate())
                .eventEndDate(source.getEventEndDate())
                .eventInfo(source.getEventInfo())
                .eventPlace(source.getEventPlace())
                .scheduleType(source.getScheduleType())
                //.filter(source.getFilter())
                //.member(source.getMember())
                .filterId(source.getFilterId())
                .memberId(source.getMemberId())
                .build();
    }


    public ScheduleModel from(Long eventId, UpdateScheduleRequest source) {
        return ScheduleModel.builder()
                .eventId(eventId)
                .eventName(source.getEventName())
                .eventStartDate(source.getEventStartDate())
                .eventEndDate(source.getEventEndDate())
                .eventInfo(source.getEventInfo())
                .eventPlace(source.getEventPlace())
                //.filterId(source.getFilterId())
                .build();
    }
}
