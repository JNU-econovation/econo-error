package com.example.demo.schedule.application.usecase;

import com.example.demo.schedule.application.dto.CreateScheduleRequest;
import com.example.demo.schedule.application.dto.CreateScheduleResponse;

public interface CreateScheduleUsecase {

    /**
     * 일정을 생성한다.
     *
     * @param requset 일정 저장을 휘한 request 객체
     * @return 일정 식별 id 전달
     */

    CreateScheduleResponse create(CreateScheduleRequest requset);
}
