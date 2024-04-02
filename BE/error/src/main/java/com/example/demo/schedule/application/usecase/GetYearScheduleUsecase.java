package com.example.demo.schedule.application.usecase;

import com.example.demo.schedule.application.dto.YearCalendarResponse;

import java.util.List;

public interface GetYearScheduleUsecase {
    List<YearCalendarResponse> getYearSchedule(int year);
}
