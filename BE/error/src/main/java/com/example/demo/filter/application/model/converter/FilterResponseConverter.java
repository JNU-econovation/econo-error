package com.example.demo.filter.application.model.converter;

import com.example.demo.filter.application.dto.CreateFilterResponse;
import org.springframework.stereotype.Component;

@Component
public class FilterResponseConverter {

    public CreateFilterResponse from(Long filterId) {
        return CreateFilterResponse.builder().filterId(filterId).build();
    }

}
