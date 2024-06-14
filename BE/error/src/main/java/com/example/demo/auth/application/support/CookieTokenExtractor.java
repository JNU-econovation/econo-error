package com.example.demo.auth.application.support;


import com.example.demo.auth.application.exception.NotFoundCookieException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component("cookie")
public class CookieTokenExtractor implements TokenExtractor {

    @Override
    public String extract(HttpServletRequest request) {
        Cookie[] cookies = getCookies(request);

        for (Cookie cookie : cookies) {
            if (Objects.equals(AuthConstants.TOKEN_KEY, cookie.getName())) {
                return getValue(cookie.getValue());
            }
        }

        throw new NotFoundCookieException();
    }

    private Cookie[] getCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            throw new NotFoundCookieException();
        }

        return cookies;
    }

    private String getValue(String value) {
        if (value != null) {
            return value;
        }
        throw new NotFoundCookieException();
    }
}
