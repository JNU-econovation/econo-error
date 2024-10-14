package com.example.demo.auth.application.config;


import com.example.demo.auth.application.model.token.TokenResolver;
import com.example.demo.auth.application.support.CookieTokenExtractor;
import com.example.demo.auth.application.support.HeaderTokenExtractor;
import com.example.demo.auth.application.support.MemberArgumentResolver;
import com.example.demo.auth.presentation.interceptor.AuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class LoginConfig implements WebMvcConfigurer {
    private final MemberArgumentResolver memberArgumentResolver;
    private final TokenResolver tokenResolver;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(memberAuthInterceptor())
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/auth/**", "/api/calendar/public/**", "/api/filter", "/api/filter/**", "/api/calendar", "/api/calendar/**", "/swagger-ui", "/swagger-ui/**");
        registry
                .addInterceptor(reissueAuthInterceptor())
                .addPathPatterns("/auth/reissue")
                .excludePathPatterns("/api/auth/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(memberArgumentResolver);
    }

    @Bean
    public AuthInterceptor memberAuthInterceptor() {
        return AuthInterceptor.builder()
                .tokenExtractor(new HeaderTokenExtractor())
                .tokenResolver(tokenResolver)
                .build();
    }

    @Bean
    public AuthInterceptor reissueAuthInterceptor() {
        return AuthInterceptor.builder()
                .tokenExtractor(new CookieTokenExtractor())
                .tokenResolver(tokenResolver)
                .build();
    }
}
