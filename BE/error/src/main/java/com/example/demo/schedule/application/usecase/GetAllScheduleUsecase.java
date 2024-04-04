package com.example.demo.schedule.application.usecase;

import com.example.demo.schedule.application.dto.AllCalendarResponse;

import java.util.List;

public interface GetAllScheduleUsecase {
    List<AllCalendarResponse> getAllSchedule();
}
