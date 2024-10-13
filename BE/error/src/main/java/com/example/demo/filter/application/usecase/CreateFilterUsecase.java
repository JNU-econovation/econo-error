package com.example.demo.filter.application.usecase;

import com.example.demo.filter.application.dto.CreateFilterRequest;
import com.example.demo.filter.application.dto.CreateFilterResponse;

public interface CreateFilterUsecase {
    CreateFilterResponse createFilter(CreateFilterRequest request, Long memberId);
}
