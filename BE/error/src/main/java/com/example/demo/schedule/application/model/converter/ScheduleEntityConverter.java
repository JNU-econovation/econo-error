package com.example.demo.schedule.application.model.converter;

import com.example.demo.common.support.converter.AbstractEntityConverter;
import com.example.demo.schedule.application.model.ScheduleModel;
import com.example.demo.schedule.persistence.ScheduleEntity;
import org.springframework.stereotype.Component;

@Component
public class ScheduleEntityConverter implements AbstractEntityConverter<ScheduleEntity, ScheduleModel> {

    @Override
    public ScheduleModel from(ScheduleEntity source) {
        return ScheduleModel.builder()
                .eventId(source.getEventId())
                .eventName(source.getEventName())
                .eventStartDate(source.getEventStartDate())
                .eventEndDate(source.getEventEndDate())
                .eventPlace(source.getEventPlace())
                .eventInfo(source.getEventInfo())
                .build();
    }

    @Override
    public ScheduleEntity toEntity(ScheduleModel source) {
        return ScheduleEntity.builder()
                .eventId(source.getEventId())
                .eventName(source.getEventName())
                .eventStartDate(source.getEventStartDate())
                .eventEndDate(source.getEventEndDate())
                .eventPlace(source.getEventPlace())
                .eventInfo(source.getEventInfo())
                .build();
    }
}
