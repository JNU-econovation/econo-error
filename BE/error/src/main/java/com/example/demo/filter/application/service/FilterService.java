package com.example.demo.filter.application.service;


import com.example.demo.filter.application.dto.*;
import com.example.demo.filter.application.model.FilterModel;
import com.example.demo.filter.application.model.converter.FilterEntityConverter;
import com.example.demo.filter.application.model.converter.FilterRequestConverter;
import com.example.demo.filter.application.model.converter.FilterResponseConverter;
import com.example.demo.filter.application.usecase.DeleteFilterUsecase;
import com.example.demo.filter.application.usecase.GetAllFilterUsecase;
import com.example.demo.filter.application.usecase.UpdateFilterUsecase;
import com.example.demo.filter.persistence.FilterEntity;
import com.example.demo.filter.persistence.FilterRepository;
import com.example.demo.filter.application.usecase.CreateFilterUsecase;
import com.example.demo.schedule.application.model.ScheduleModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FilterService implements
                                    CreateFilterUsecase,
                                    GetAllFilterUsecase,
                                    DeleteFilterUsecase,
                                    UpdateFilterUsecase {

    private final FilterRepository filterRepository;
    private final FilterRequestConverter requestConverter;
    private final FilterEntityConverter entityConverter;
    private final FilterResponseConverter responseConverter;


    @Override
    @Transactional
    public CreateFilterResponse createFilter(final CreateFilterRequest request) {
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

    @Override
    @Transactional
    public void delete(final Long filterId) {
        FilterModel filter = findFilter(filterId);
        filterRepository.deleteById(filter.getFilterId());
    }

    private FilterModel findFilter(final Long filterId) {
        return filterRepository
                .findById(filterId)
                .map(entityConverter::from)
                .orElseThrow(() -> new NoSuchElementException("no found eventId :" + filterId));
    }

    @Override
    @Transactional
    public UpdateFilteResponse update(Long filterId, UpdateFilterRequest request) {
        FilterModel model = findFilter(filterId);
        FilterModel requestModel = requestConverter.from(filterId, request);
        updateFilter(model, requestModel);

        return responseConverter.fromUpdate(model.getFilterId());
    }

    private void updateFilter(FilterModel model, FilterModel requestModel) {
        FilterModel update = model.update(requestModel);
        FilterEntity entity = entityConverter.toEntity(update);
        filterRepository.save(entity);
    }
}
