package com.example.demo.filter.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class UpdateFilterRequest {
    private String filterName;
    private String filterColor;
    private Long memberId;
}
