package com.example.demo.auth.presentation;

import com.example.demo.auth.application.dto.TokenResponse;
import com.example.demo.auth.application.model.TokenModel;
import com.example.demo.auth.application.model.converter.TokenResponseConverter;
import com.example.demo.auth.application.support.AuthConstants;
import com.example.demo.auth.application.support.HeaderTokenExtractor;
import com.example.demo.auth.application.support.TokenExtractor;
import com.example.demo.auth.application.usecase.LoginUsecase;
import com.example.demo.auth.application.usecase.ReissueUsecase;
import com.example.demo.common.presentation.response.ApiResponse;
import com.example.demo.common.presentation.response.ApiResponseBody.SuccessBody;
import com.example.demo.common.presentation.response.ApiResponseGenerator;
import com.example.demo.common.presentation.response.MessageCode;
import com.example.demo.common.support.CookieManager;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
//@RequiredArgsConstructor
public class AuthController {

    private final LoginUsecase loginUsecase;
    private final ReissueUsecase reissueUsecase;
    private final TokenResponseConverter tokenResponseConverter;
    private final CookieManager cookieManager;
    private final TokenExtractor tokenExtractor;


    public AuthController(LoginUsecase loginUsecase, TokenResponseConverter tokenResponseConverter, CookieManager cookieManager, TokenExtractor tokenExtractor) {
        this.loginUsecase = loginUsecase;
        this.tokenResponseConverter = tokenResponseConverter;
        this.cookieManager = cookieManager;
        this.tokenExtractor = tokenExtractor;
    }

    @Operation(
            summary = "로그인을 한다.",
            description = "PathVariable에 담긴 oauthServerType과 QuertParm인 redirect_url, code를 받아 액세스 토큰과 리프레시 토큰을 발급한다.")
    @PostMapping("/login/{oauthServerType}")
    ApiResponse<SuccessBody<TokenResponse>> login(
            @PathVariable String oauthServerType,
            @RequestParam("code") String code,
            @RequestParam("redirect_uri") String uri,
            HttpServletResponse httpResponse) {
        TokenModel tokenModel = loginUsecase.login(oauthServerType, code, uri);
        TokenResponse response = generateTokenResponse(tokenModel, httpResponse);

        return ApiResponseGenerator.success(response, HttpStatus.CREATED, MessageCode.CREATE);
    }

    @Operation(summary = "토큰을 재발급한다.", description = "쿠키에 담긴 사용자 토큰을 이용하여 리프레시 토큰을 반환한다.")
    @PostMapping("/reissue")
    ApiResponse<SuccessBody<TokenResponse>> reissue(HttpServletRequest request, HttpServletResponse httpResonse) {
        String token = tokenExtractor.extract(request);
        TokenModel tokenModel = reissueUsecase.execute(token);
        TokenResponse response = generateTokenResponse(tokenModel, httpResonse);

        return ApiResponseGenerator.success(response, HttpStatus.CREATED, MessageCode.CREATE);
    }


    private TokenResponse generateTokenResponse(
            TokenModel tokenModel, HttpServletResponse httpServletResponse) {
        TokenResponse response =
                tokenResponseConverter.from(tokenModel.getAccessToken(), tokenModel.getAccessExpiredTime());

        ResponseCookie cookie =
                cookieManager.setCookie(AuthConstants.TOKEN_KEY, tokenModel.getRefreshToken());
        httpServletResponse.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return response;
    }

}
