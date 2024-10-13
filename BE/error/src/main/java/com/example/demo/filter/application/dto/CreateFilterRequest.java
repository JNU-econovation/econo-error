package com.example.demo.filter.application.dto;


import com.example.demo.auth.persistence.MemberEntity;
import com.example.demo.common.support.dto.AbstractDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CreateFilterRequest implements AbstractDto {
    private String filterName;
    private String filterColor;
    private MemberEntity member;
}
