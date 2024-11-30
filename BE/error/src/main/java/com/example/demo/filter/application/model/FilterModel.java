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
    private MemberEntity member;
    private final FilterRepository filterRepository;

    public FilterModel update(FilterModel requestModel) {
        filterName = requestModel.getFilterName();
        filterColor = requestModel.filterColor;
        return this;
    }

    public String findFilterColor(Long filterId) {
        String filterColor = filterRepository.findFilterColorByFilterId(filterId);
        return filterColor;
    }

    public FilterModel getFilterModel(Long filterId) {
        // 데이터베이스에서 filterId로 FilterEntity 조회
        return this;
    }
}
