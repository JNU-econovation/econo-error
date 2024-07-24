package com.example.demo.filter.presentation;

import com.example.demo.common.presentation.response.ApiResponse;
import com.example.demo.common.presentation.response.ApiResponseBody;
import com.example.demo.common.presentation.response.ApiResponseGenerator;
import com.example.demo.common.presentation.response.MessageCode;
import com.example.demo.filter.application.dto.AllFilterResponse;
import com.example.demo.filter.application.dto.CreateFilterRequest;
import com.example.demo.filter.application.dto.CreateFilterResponse;
import com.example.demo.filter.application.usecase.CreateFilterUsecase;
import com.example.demo.filter.application.usecase.GetAllFilterUsecase;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/filter")
public class FilterController {

    private final CreateFilterUsecase createFilterUsecase;
    private final GetAllFilterUsecase getAllFilterUsecase;

    @PostMapping
    public ApiResponse<ApiResponseBody.SuccessBody<CreateFilterResponse>> createFilter(
            @RequestBody CreateFilterRequest request) {
        CreateFilterResponse response = createFilterUsecase.createFilter(request);
        return ApiResponseGenerator.success(response, HttpStatus.OK, MessageCode.CREATE);
    }

    @GetMapping
    public ApiResponse<ApiResponseBody.SuccessBody<List<AllFilterResponse>>> getFilter() {
        List<AllFilterResponse> response = getAllFilterUsecase.getFilter();
        return ApiResponseGenerator.success(response, HttpStatus.OK, MessageCode.GET);
    }

}