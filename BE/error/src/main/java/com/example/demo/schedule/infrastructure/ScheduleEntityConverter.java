package com.example.demo.schedule.infrastructure;

import com.example.demo.common.support.converter.AbstractEntityConverter;
import com.example.demo.schedule.domain.model.ScheduleModel;
import com.example.demo.schedule.infrastructure.persistence.ScheduleEntity;
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
                .eventInfo(source.getEventInfo())
                .eventPlace(source.getEventPlace())
                .scheduleType(source.getScheduleType())
                //.filter(source.getFilter())
                .memberId(source.getMemberId())
                .filterId(source.getFilterId())
                .build();
    }

    @Override
    public ScheduleEntity toEntity(ScheduleModel source) {
        return ScheduleEntity.builder()
                .eventId(source.getEventId())
                .eventName(source.getEventName())
                .eventStartDate(source.getEventStartDate())
                .eventEndDate(source.getEventEndDate())
                .eventInfo(source.getEventInfo())
                .eventPlace(source.getEventPlace())
                .scheduleType(source.getScheduleType())
                .filterId(source.getFilterId())
                .memberId(source.getMemberId())
                .build();
    }


}
