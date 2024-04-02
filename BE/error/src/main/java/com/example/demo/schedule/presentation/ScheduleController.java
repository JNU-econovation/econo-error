package com.example.demo.schedule.presentation;

import com.example.demo.common.presentation.response.ApiResponse;
import com.example.demo.common.presentation.response.ApiResponseBody.SuccessBody;
import com.example.demo.common.presentation.response.ApiResponseGenerator;
import com.example.demo.common.presentation.response.MessageCode;
import com.example.demo.schedule.application.dto.*;
import com.example.demo.schedule.application.usecase.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/calendar")
public class ScheduleController {

    private final CreateScheduleUsecase createScheduleUsecase;
    private final GetSpecificScheduleUsecase getSpecificScheduleUsecase;
    private final GetYearScheduleUsecase getYearScheduleUsecase;
    private final GetMonthScheduleUsecase getMonthScheduleUsecase;
    private final UpdateScheduleUsecase updateScheduleUsecase;
    private final DeleteScheduleUsecase deleteScheduleUsecase;

    @PostMapping
    public ApiResponse<SuccessBody<CreateScheduleResponse>> create(
            @RequestBody CreateScheduleRequest request) {

        CreateScheduleResponse response = createScheduleUsecase.create(request);
        return ApiResponseGenerator.success(response, HttpStatus.OK, MessageCode.CREATE);
    }

    @GetMapping("/{eventId}")
    public ApiResponse<SuccessBody<SpecificScheduleResopnse>> getSpecificCalendar (
            @PathVariable("eventId") Long eventId
    ) {
        SpecificScheduleResopnse response = getSpecificScheduleUsecase.getSpecificSchedule(eventId);
        return ApiResponseGenerator.success(response, HttpStatus.OK, MessageCode.GET);
    }

    @GetMapping("year/{year}-{month}-{day}")
    public ApiResponse<SuccessBody<List<YearCalendarResponse>>> getYearCalendar (
            @PathVariable("year") int year
    ) {
        List<YearCalendarResponse> response = getYearScheduleUsecase.getYearSchedule(year);
        return ApiResponseGenerator.success(response, HttpStatus.OK, MessageCode.GETYEAR);
    }

    @GetMapping("month/{year}-{month}-{day}")
    public ApiResponse<SuccessBody<List<MonthCalendarResponse>>> getMonthCalendar(
            @PathVariable("year") int year,
            @PathVariable("month") int month
    ) {
        List<MonthCalendarResponse> response = getMonthScheduleUsecase.getMonthSchedule(year, month);
        return ApiResponseGenerator.success(response, HttpStatus.OK, MessageCode.GETMONTH);
    }

    @PutMapping("/{eventId}")
    public ApiResponse<SuccessBody<UpdateScheduleResponse>> update(
            @RequestBody UpdateScheduleRequest updateScheduleRequest,
            @PathVariable("eventId") Long eventId) {// 수정하는 logic


        UpdateScheduleResponse response = updateScheduleUsecase.update(eventId, updateScheduleRequest);
        return ApiResponseGenerator.success(response, HttpStatus.OK, MessageCode.UPDATE);
    }

}
