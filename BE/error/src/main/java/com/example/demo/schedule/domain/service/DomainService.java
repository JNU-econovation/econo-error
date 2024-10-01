package com.example.demo.schedule.domain.service;

import com.example.demo.schedule.application.model.ScheduleModel;
import com.example.demo.schedule.application.model.converter.ScheduleEntityConverter;
import com.example.demo.schedule.domain.ScheduleEntity;
import com.example.demo.schedule.domain.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.convert.EntityConverter;

@RequiredArgsConstructor
public class DomainService {


    private final ScheduleEntityConverter entityConverter;
    private final ScheduleRepository scheduleRepository;


    private Long createSchedule(ScheduleModel model) {

        ScheduleEntity entity = entityConverter.toEntity(model);
        ScheduleEntity save = scheduleRepository.save(entity);
        return save.getEventId();
    }
}
