package com.example.demo.filter.application.model.converter;

import com.example.demo.common.support.converter.AbstractEntityConverter;
import com.example.demo.filter.application.model.FilterModel;
import com.example.demo.filter.persistence.FilterEntity;
import org.springframework.stereotype.Component;

@Component
public class FilterEntityConverter implements AbstractEntityConverter<FilterEntity, FilterModel> {

    @Override
    public FilterModel from(FilterEntity source) {
        return FilterModel.builder()
                .filterId(source.getFilterId())
                .filterName(source.getFilterName())
                .filterColor(source.getFilterColor())
                .memberId(source.getMemberId())
                .build();
    }

    @Override
    public FilterEntity toEntity(FilterModel source) {
        return FilterEntity.builder()
                .filterId(source.getFilterId())
                .filterName(source.getFilterName())
                .filterColor(source.getFilterColor())
                .memberId(source.getMemberId())
                .build();
    }
}
