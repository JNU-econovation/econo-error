package com.example.demo.filter.presentation;

import com.example.demo.common.presentation.response.ApiResponse;
import com.example.demo.common.presentation.response.ApiResponseBody;
import com.example.demo.common.presentation.response.ApiResponseGenerator;
import com.example.demo.common.presentation.response.MessageCode;
import com.example.demo.filter.application.dto.CreateFilterRequest;
import com.example.demo.filter.application.dto.CreateFilterResponse;
import com.example.demo.schedule.application.dto.CreateScheduleResponse;
import com.example.demo.schedule.application.usecase.CreateFilterUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/filter")
public class FilterController {

    private final CreateFilterUsecase createFilterUsecase;

    @PostMapping
    public ApiResponse<ApiResponseBody.SuccessBody<CreateFilterResponse>> createFilter(
            @RequestBody CreateFilterRequest request) {
        CreateFilterResponse response = createFilterUsecase.createFilter(request);
        return ApiResponseGenerator.success(response, HttpStatus.OK, MessageCode.CREATE);
    }



























}
