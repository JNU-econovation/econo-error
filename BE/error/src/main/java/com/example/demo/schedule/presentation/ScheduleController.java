package com.example.demo.schedule.presentation;

import com.example.demo.common.presentation.response.ApiResponse;
import com.example.demo.common.presentation.response.ApiResponseBody.SuccessBody;
import com.example.demo.common.presentation.response.ApiResponseGenerator;
import com.example.demo.common.presentation.response.MessageCode;
import com.example.demo.schedule.application.dto.CreateScheduleRequest;
import com.example.demo.schedule.application.dto.UpdateScheduleRequest;
import com.example.demo.schedule.application.dto.YearCalendarResponse;
import com.sun.net.httpserver.Authenticator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ScheduleController {

    @PostMapping("/calendar")
    public ApiResponse<SuccessBody<Void>> create(
            @RequestBody CreateScheduleRequest createScheduleRequest) {
        // 일정 생성하는 service logic
        return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.CREATE);
    }

    @GetMapping("/calendar/{eventId}")
    public ApiResponse<SuccessBody<YearCalendarResponse>> getSpecificCalendar (
            @PathVariable("eventId") Long eventId) {
        // specific response 담아서 리턴 logic
        return ApiResponseGenerator.success(response, HttpStatus.OK, MessageCode.GET);
    }

    @GetMapping("/calendar")
    public ApiResponse<SuccessBody<YearCalendarResponse>> getTypeCalendar (
            @RequestParam("period") String type) {
        // type 별 response 담아서 리턴 logic
        return ApiResponseGenerator.success(response, HttpStatus.OK, MessageCode.GET);
    }

    @PutMapping("/calendar/{eventId}")
    public ApiResponse<SuccessBody<Void>> update(
            @RequestBody UpdateScheduleRequest updateScheduleRequest,
            @PathVariable("eventId") Long eventId) {
        // 수정하는 logic
        return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.UPDATE);
    }

    @DeleteMapping("/calendar/{eventId}")
    public ApiResponse<SuccessBody<Void>> delete(
            @PathVariable("eventId") Long eventId) {
        // 일정 삭제하는 logic
        return return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.DELETE);
    }

}
