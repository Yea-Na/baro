package com.sparta.baro_15.controller;

import com.sparta.baro_15.config.JwtUtil;
import com.sparta.baro_15.dto.ReqSigninDto;
import com.sparta.baro_15.dto.ReqSignupDto;
import com.sparta.baro_15.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "인증 API", description = "회원가입 및 로그인 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/auth")
public class AuthController {

    private final AuthService authService;

    //회원가입
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody @Valid ReqSignupDto reqDto){
            authService.signUp(reqDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("회원가입이 성공적으로 완료되었습니다.");
    }

    //로그인
    @PostMapping("/signin")
    public ResponseEntity<String> signin(@RequestBody @Valid ReqSigninDto reqDto){
        String accessToken = authService.signIn(reqDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add(JwtUtil.AUTHORIZATION_HEADER, JwtUtil.BEARER_PREFIX + accessToken);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body("로그인이 성공적으로 완료되었습니다.");
    }
}
