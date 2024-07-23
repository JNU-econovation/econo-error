package com.example.demo.filter.application.service;

import com.example.demo.filter.application.dto.CreateFilterRequest;
import com.example.demo.filter.application.dto.CreateFilterResponse;
import com.example.demo.filter.application.model.FilterModel;
import com.example.demo.filter.application.model.converter.FilterEntityConverter;
import com.example.demo.filter.application.model.converter.FilterRequestConverter;
import com.example.demo.filter.application.model.converter.FilterResponseConverter;
import com.example.demo.filter.persistence.FilterEntity;
import com.example.demo.filter.persistence.FilterRepository;
import com.example.demo.schedule.application.usecase.CreateFilterUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FilterService implements CreateFilterUsecase {

    private final FilterRepository filterRepository;
    private final FilterRequestConverter requestConverter;
    private final FilterEntityConverter entityConverter;
    private final FilterResponseConverter responseConverter;


    @Override
    public CreateFilterResponse createFilter(CreateFilterRequest request) {
        FilterModel model = requestConverter.from(request);
        FilterEntity entity = entityConverter.toEntity(model);
        FilterEntity save = filterRepository.save(entity);
        return responseConverter.from(save.getFilterId());
    }
}
