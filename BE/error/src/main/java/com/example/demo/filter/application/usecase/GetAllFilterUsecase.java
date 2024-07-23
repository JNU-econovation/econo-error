package com.example.demo.filter.application.usecase;

import com.example.demo.filter.application.dto.AllFilterResponse;
import com.example.demo.filter.application.dto.CreateFilterRequest;
import com.example.demo.filter.application.dto.CreateFilterResponse;

import java.util.List;

public interface GetAllFilterUsecase {
    List<AllFilterResponse> getFilter();
}
