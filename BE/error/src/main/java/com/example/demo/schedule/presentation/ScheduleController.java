package com.example.demo.schedule.presentation;

import com.example.demo.common.presentation.response.ApiResponse;
import com.example.demo.common.presentation.response.ApiResponseBody.SuccessBody;
import com.example.demo.common.presentation.response.ApiResponseGenerator;
import com.example.demo.common.presentation.response.MessageCode;
import com.example.demo.schedule.application.dto.*;
import com.example.demo.schedule.application.usecase.CreateScheduleUsecase;
import com.example.demo.schedule.application.usecase.GetSpecificScheduleUsecase;
import com.example.demo.schedule.application.usecase.GetYearScheduleUsecase;
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


    @PostMapping
    public ApiResponse<SuccessBody<CreateScheduleResponse>> create(
            @RequestBody CreateScheduleRequest request) {

        CreateScheduleResponse response = createScheduleUsecase.create(request);
        return ApiResponseGenerator.success(response, HttpStatus.OK, MessageCode.CREATE);
    }

    @GetMapping("/{eventId}")
    public ApiResponse<SuccessBody<SpecificScheduleResopnse>> getSpecificCalendar (
            @PathVariable("eventId") Long eventId) {

        SpecificScheduleResopnse response = getSpecificScheduleUsecase.getSpecificSchedule(eventId);
        return ApiResponseGenerator.success(response, HttpStatus.OK, MessageCode.GET);
    }


    @GetMapping("year/{year}-{month}-{day}")
    public ApiResponse<SuccessBody<List<YearCalendarResponse>>> getYearCalendar (
            @PathVariable("year") int year) {

        List<YearCalendarResponse> response = getYearScheduleUsecase.getYearSchedule(year);
        return ApiResponseGenerator.success(response, HttpStatus.OK, MessageCode.GET);
    }
//
//    @PutMapping("/calendar/{eventId}")
//    public ApiResponse<SuccessBody<Void>> update(
//            @RequestBody UpdateScheduleRequest updateScheduleRequest,
//            @PathVariable("eventId") Long eventId) {// 수정하는 logic
//
//
//        return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.UPDATE);
//    }
//
//    @DeleteMapping("/calendar/{eventId}")
//    public ApiResponse<SuccessBody<Void>> delete(
//            @PathVariable("eventId") Long eventId) {
//        // 일정 삭제하는 logic
//        return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.DELETE);
//    }

}
