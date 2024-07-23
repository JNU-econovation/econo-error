package com.example.demo.auth.application.support;

import jakarta.servlet.http.HttpServletRequest;

public interface TokenExtractor {

    String extract(HttpServletRequest request);
}