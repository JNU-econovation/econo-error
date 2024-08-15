package com.example.demo.schedule.application.usecase;

import com.example.demo.schedule.application.dto.AllCalendarResponse;
import com.example.demo.schedule.application.dto.AllPrivateCalendarResponse;
import com.example.demo.schedule.application.dto.AllPublicCalendarResponse;

import java.util.List;

public interface GetAllScheduleUsecase {
    List<AllCalendarResponse> getAllSchedule();

    List<AllPublicCalendarResponse> getPublicSchedule();

    List<AllPrivateCalendarResponse> getPrivateSchedule();
}
