package com.example.demo.schedule.application.usecase;

import com.example.demo.schedule.application.dto.SpecificScheduleResopnse;

public interface GetSpecificScheduleUsecase {

    SpecificScheduleResopnse getSpecificSchedule(Long eventId);
}
