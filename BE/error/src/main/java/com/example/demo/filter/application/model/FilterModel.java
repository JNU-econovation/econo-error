package com.example.demo.filter.application.model;

import com.example.demo.auth.persistence.MemberEntity;
import com.example.demo.common.support.AbstractModel;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class FilterModel implements AbstractModel {
    private Long filterId;
    private String filterName;
    private String filterColor;
    private MemberEntity member;

    public FilterModel update(FilterModel requestModel) {
        filterName = requestModel.getFilterName();
        filterColor = requestModel.filterColor;

        return this;
    }
}
