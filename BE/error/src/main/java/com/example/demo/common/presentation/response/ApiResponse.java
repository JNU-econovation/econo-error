package com.example.demo.common.presentation.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public class ApiResponse<B> extends ResponseEntity<B> {

    public ApiResponse(final HttpStatus status) {
        super(status);
    }

    public ApiResponse(final B body, final HttpStatus status) {
        super(body, status);
    }

    public ApiResponse(final B body, MultiValueMap<String, String> headers, HttpStatus status) {
        super(body, headers, status);
    }
}
