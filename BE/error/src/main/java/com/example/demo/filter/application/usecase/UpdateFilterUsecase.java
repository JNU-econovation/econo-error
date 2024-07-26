package com.example.demo.filter.application.usecase;

import com.example.demo.filter.application.dto.UpdateFilteResponse;
import com.example.demo.filter.application.dto.UpdateFilterRequest;

public interface UpdateFilterUsecase {
    UpdateFilteResponse update(Long filterId, UpdateFilterRequest request);
}
