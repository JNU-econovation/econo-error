package com.example.demo.filter.presentation;

import com.example.demo.common.presentation.response.ApiResponse;
import com.example.demo.common.presentation.response.ApiResponseBody;
import com.example.demo.common.presentation.response.ApiResponseGenerator;
import com.example.demo.common.presentation.response.MessageCode;
import com.example.demo.filter.application.dto.*;
import com.example.demo.filter.application.usecase.CreateFilterUsecase;
import com.example.demo.filter.application.usecase.DeleteFilterUsecase;
import com.example.demo.filter.application.usecase.GetAllFilterUsecase;
import com.example.demo.filter.application.usecase.UpdateFilterUsecase;
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
    private final DeleteFilterUsecase deleteFilterUsecase;
    private final UpdateFilterUsecase updateFilterUsecase;

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

    @DeleteMapping("/{filterId}")
    public ApiResponse<ApiResponseBody.SuccessBody<Void>> delete(
            @PathVariable("filterId") Long filterId
    ) {
        deleteFilterUsecase.delete(filterId);
        return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.DELETE);
    }


    @PutMapping("/{filterId}")
    public ApiResponse<ApiResponseBody.SuccessBody<UpdateFilteResponse>> update(
            @RequestBody UpdateFilterRequest updateFilterRequest,
            @PathVariable("filterId") Long filterId)
    {
        UpdateFilteResponse response = updateFilterUsecase.update(filterId, updateFilterRequest);
        return ApiResponseGenerator.success(response, HttpStatus.OK, MessageCode.UPDATE);
    }

}
