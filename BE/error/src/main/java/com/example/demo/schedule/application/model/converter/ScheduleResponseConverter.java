package com.example.demo.schedule.application.model.converter;

import com.example.demo.schedule.application.dto.CreateScheduleResponse;
import org.springframework.stereotype.Component;

@Component
public class ScheduleResponseConverter {


    public CreateScheduleResponse from(Long id) {
        return CreateScheduleResponse.builder().eventId(id).build();
    }
}
