package com.example.demo.schedule.application.usecase;

import com.example.demo.schedule.application.dto.MonthCalendarResponse;

import java.util.List;

public interface GetMonthScheduleUsecase {
    List<MonthCalendarResponse> getMonthSchedule(int year, int month);
}
