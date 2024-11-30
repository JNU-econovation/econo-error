package com.example.demo.schedule.presentation;

import com.example.demo.common.presentation.response.ApiResponse;
import com.example.demo.common.presentation.response.ApiResponseBody.SuccessBody;
import com.example.demo.common.presentation.response.ApiResponseGenerator;
import com.example.demo.common.presentation.response.MessageCode;
import com.example.demo.schedule.application.dto.*;
import com.example.demo.schedule.application.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/calendar")
public class ScheduleController {

    private final ScheduleService scheduleService;


    @PostMapping
    public ApiResponse<SuccessBody<CreateScheduleResponse>> create(
            @RequestBody CreateScheduleRequest request) {

        CreateScheduleResponse response = scheduleService.createSchedule(request);
        return ApiResponseGenerator.success(response, HttpStatus.OK, MessageCode.CREATE);
    }


    @PutMapping("/{eventId}")
    public ApiResponse<SuccessBody<UpdateScheduleResponse>> update(
            @RequestBody UpdateScheduleRequest updateScheduleRequest,
            @PathVariable("eventId") Long eventId) {

        UpdateScheduleResponse response = scheduleService.updateSchedule(eventId, updateScheduleRequest);
        return ApiResponseGenerator.success(response, HttpStatus.OK, MessageCode.UPDATE);
    }


    @DeleteMapping("/{eventId}")
    public ApiResponse<SuccessBody<Void>> delete(
            @PathVariable("eventId") Long eventId) {

        scheduleService.deleteSchedule(eventId);
        return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.DELETE);
    }


    @GetMapping("/{eventId}")
    public ApiResponse<SuccessBody<SpecificScheduleResopnse>> getSpecificCalendar(
            @PathVariable("eventId") Long eventId) {

        SpecificScheduleResopnse response = scheduleService.getSpecificSchedule(eventId);
        return ApiResponseGenerator.success(response, HttpStatus.OK, MessageCode.GET);
    }


    @GetMapping("all/public")
    public ApiResponse<SuccessBody<List<AllPublicCalendarResponse>>> getPublic() {
        List<AllPublicCalendarResponse> response = scheduleService.getPublicSchedule();
        return ApiResponseGenerator.success(response, HttpStatus.OK, MessageCode.GETALL);
    }


    @GetMapping("all/private")
    public ApiResponse<SuccessBody<List<AllPrivateCalendarResponse>>> getPrivate() {
        List<AllPrivateCalendarResponse> response = scheduleService.getPrivateSchedule();
        return ApiResponseGenerator.success(response, HttpStatus.OK, MessageCode.GETALL);
    }

//    @GetMapping("slack/test")
//    public void slackTest() {
//
//        String event = slackService.makeSlackMessage();
//        slackService.sendSlackMessage(event, "test");
//    }


    // 일정 조회를 어떻게 리팩토링 할 수 있을까?
    // 하나의 요청 uri를 가지고 내부
    // 토큰이 존재한다면? 토근에서 값을 추출 후 memberId에 맞는 private 일정 + public 일정 응답
    // 토큰이 존재하지 않다면? public 일정만 응답
}
