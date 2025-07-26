package com.sparta.baro_15.config;

import java.security.Key;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private Key key; // 시크릿 키를 저장할 Key 타입 변수

    @Value("${jwt.secret}")
    private String secretString; // application.yml에서 읽어올 String 변수

    @Value("${jwt.accessTokenExpiration}")
    private Long accessTokenExpiration;

    //generateAccessToken

    //isvalid

    //parseToken


}
