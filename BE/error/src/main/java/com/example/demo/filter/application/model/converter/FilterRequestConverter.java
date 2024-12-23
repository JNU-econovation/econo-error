package com.example.demo.filter.application.model.converter;

import com.example.demo.common.support.converter.AbstractDtoConverter;
import com.example.demo.filter.application.dto.CreateFilterRequest;
import com.example.demo.filter.application.dto.UpdateFilterRequest;
import com.example.demo.filter.application.model.FilterModel;
import org.springframework.stereotype.Component;

@Component
public class FilterRequestConverter {

    public FilterModel from(CreateFilterRequest source, Long memberId) {
        return FilterModel.builder()
                //.filterId(source.getFilterId())
                .filterName(source.getFilterName())
                .filterColor(source.getFilterColor())
                .memberId(memberId)
                //.member(memberId)
                //.member(source.getMember())
                .build();
    }

    public FilterModel from(Long filterId, UpdateFilterRequest source) {
        return FilterModel.builder()
                .filterId(filterId)
                .filterName(source.getFilterName())
                .filterColor(source.getFilterColor())
                .memberId(source.getMemberId())
                .build();
    }






}
