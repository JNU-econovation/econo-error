package com.example.demo.filter.application.model.converter;


import com.example.demo.filter.application.dto.AllFilterResponse;
import com.example.demo.filter.application.dto.CreateFilterResponse;
import com.example.demo.filter.application.dto.UpdateFilteResponse;
import com.example.demo.filter.application.model.FilterModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class FilterResponseConverter {

    public CreateFilterResponse from(Long filterId) {
        return CreateFilterResponse.builder().filterId(filterId).build();
    }


    public List<AllFilterResponse> toAllModel(List<FilterModel> models) {
        List<AllFilterResponse> response = new ArrayList<>();
        for (FilterModel model : models) {
            AllFilterResponse filter = AllFilterResponse.builder()
                    .filterId(model.getFilterId())
                    .filterName(model.getFilterName())
                    .filterColor(model.getFilterColor())
                    .build();
            response.add(filter);
        }
        return response;
    }

    public UpdateFilteResponse fromUpdate(Long filterId) {
        return UpdateFilteResponse.builder().filterId(filterId).build();
    }

}
