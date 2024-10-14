package com.example.demo.filter.application.model.converter;

import com.example.demo.common.support.converter.AbstractDtoConverter;
import com.example.demo.filter.application.dto.CreateFilterRequest;
import com.example.demo.filter.application.dto.UpdateFilterRequest;
import com.example.demo.filter.application.model.FilterModel;
import org.springframework.stereotype.Component;

@Component
public class FilterRequestConverter implements AbstractDtoConverter<CreateFilterRequest, FilterModel> {


    @Override
    public FilterModel from(CreateFilterRequest source) {
        return FilterModel.builder()
                //.filterId(source.getFilterId())
                .filterName(source.getFilterName())
                .filterColor(source.getFilterColor())
                //.member(source.getMember())
                .build();
    }

    public FilterModel from(Long filterId, UpdateFilterRequest source) {
        return FilterModel.builder()
                .filterId(source.getFilterId())
                .filterName(source.getFilterName())
                .filterColor(source.getFilterColor())
                .build();
    }






}
