package com.example.demo.schedule.application.usecase;

import com.example.demo.schedule.application.dto.WeekCalendarResponse;

import java.util.List;

public interface GetWeekScheduleUsecase {
    //List<MonthCalendarResponse> getMonthSchedule(int year, int month);
    List<WeekCalendarResponse> getWeekSchedule(int year, int month, int day);
}
