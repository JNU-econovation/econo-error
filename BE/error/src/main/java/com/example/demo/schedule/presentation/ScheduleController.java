package com.example.demo.schedule.presentation;

import com.example.demo.common.presentation.response.ApiResponse;
import com.example.demo.common.presentation.response.ApiResponseBody.SuccessBody;
import com.example.demo.common.presentation.response.ApiResponseGenerator;
import com.example.demo.common.presentation.response.MessageCode;
import com.example.demo.schedule.application.dto.*;
import com.example.demo.schedule.application.service.ScheduleService;
import com.example.demo.schedule.infrastructure.SlackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/calendar")
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final SlackService slackService;


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

    @GetMapping("slack/test")
    @Scheduled(cron = "0 0 9 * * MON")
    public void sendSlackMessage() {

        String event = slackService.makeSlackMessage();
        slackService.sendSlackMessage(event, "test");
    }
}
