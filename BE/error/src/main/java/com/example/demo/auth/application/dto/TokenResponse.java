package com.example.demo.auth.application.dto;

import com.example.demo.common.support.dto.AbstractDto;
import com.example.demo.common.support.dto.AbstractResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TokenResponse implements AbstractResponseDto {
    private String accessToken;
    private Long accessExpiredTime;

}
