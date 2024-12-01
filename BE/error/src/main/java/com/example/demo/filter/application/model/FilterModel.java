package com.example.demo.filter.application.model;

import com.example.demo.auth.persistence.MemberEntity;
import com.example.demo.common.support.AbstractModel;
import com.example.demo.filter.persistence.FilterEntity;
import com.example.demo.filter.persistence.FilterRepository;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@Builder(toBuilder = true)
@RequiredArgsConstructor
public class FilterModel implements AbstractModel {
    private Long filterId;
    private String filterName;
    private String filterColor;
    private Long memberId;


    public FilterModel update(FilterModel requestModel) {
        filterId = requestModel.getFilterId();
        filterName = requestModel.getFilterName();
        filterColor = requestModel.getFilterColor();
        memberId = requestModel.getMemberId();
        return this;
    }
}
