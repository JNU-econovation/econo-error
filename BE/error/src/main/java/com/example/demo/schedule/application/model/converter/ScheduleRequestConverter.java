package com.example.demo.schedule.application.model.converter;

import com.example.demo.common.support.converter.AbstractDtoConverter;
import com.example.demo.schedule.application.dto.CreateScheduleRequest;
import com.example.demo.schedule.application.dto.UpdateScheduleRequest;
import com.example.demo.schedule.application.model.ScheduleModel;
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
                .build();
    }


    public ScheduleModel from(Long eventId, UpdateScheduleRequest source) {
        return ScheduleModel.builder()
                .eventId(source.getEventId())
                .eventName(source.getEventName())
                .eventStartDate(source.getEventStartDate())
                .eventEndDate(source.getEventEndDate())
                .eventInfo(source.getEventInfo())
                .eventPlace(source.getEventPlace())
                .build();
    }
    /*public ScheduleModel from(CreateScheduleRequest request) {
        return ScheduleModel.builder()
                .eventName(request.getEventName())
                .eventStartDate(request.getEventStartDate())
                .eventEndDate(request.getEventEndDate())
                .eventInfo(request.getEventInfo())
                .eventPlace(request.getEventPlace())
                .build();
    }*/
}
