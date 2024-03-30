package com.example.demo.common.presentation.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

public class ApiResponseBody {
    @Getter
    @AllArgsConstructor
    public static class FailureBody implements Serializable { //직렬화를 왜 한거지?
        private String status;
        private String code;
        private String message;
    }

    @Getter
    @AllArgsConstructor
    public static class SuccessBody<D> implements Serializable {
        private D data;
        private String message;
        private String code;
    }
}