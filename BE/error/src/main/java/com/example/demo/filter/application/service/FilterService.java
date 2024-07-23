package com.example.demo.filter.application.service;

import com.example.demo.filter.application.dto.AllFilterResponse;
import com.example.demo.filter.application.dto.CreateFilterRequest;
import com.example.demo.filter.application.dto.CreateFilterResponse;
import com.example.demo.filter.application.model.FilterModel;
import com.example.demo.filter.application.model.converter.FilterEntityConverter;
import com.example.demo.filter.application.model.converter.FilterRequestConverter;
import com.example.demo.filter.application.model.converter.FilterResponseConverter;
import com.example.demo.filter.application.usecase.GetAllFilterUsecase;
import com.example.demo.filter.persistence.FilterEntity;
import com.example.demo.filter.persistence.FilterRepository;
import com.example.demo.filter.application.usecase.CreateFilterUsecase;
import com.example.demo.schedule.application.model.ScheduleModel;
import com.example.demo.schedule.persistence.ScheduleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FilterService implements
                                    CreateFilterUsecase,
                                    GetAllFilterUsecase {

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

    @Override
    public List<AllFilterResponse> getFilter() {
        List<FilterModel> model = filterEntitiesByAll();
        return responseConverter.toAllModel(model);
    }

    private List<FilterModel> filterEntitiesByAll() {
        Stream<FilterEntity> entities = filterRepository.streamAll();
        return entities
                .map(entityConverter::from)
                .collect(Collectors.toList());
    }
}
