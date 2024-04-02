package com.example.demo.schedule.application.usecase;

import com.example.demo.schedule.application.dto.UpdateScheduleRequest;
import com.example.demo.schedule.application.dto.UpdateScheduleResponse;
import org.springframework.stereotype.Component;

@Component
public interface UpdateScheduleUsecase {
    UpdateScheduleResponse update(Long eventId, UpdateScheduleRequest request);
}
