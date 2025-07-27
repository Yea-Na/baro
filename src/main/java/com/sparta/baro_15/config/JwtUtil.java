package com.sparta.baro_15.config;

import com.sparta.baro_15.domain.UserRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j(topic = "JWT_UTIL")
@Component
@RequiredArgsConstructor
public class JwtUtil {
    public static final String AUTHORIZATION_HEADER = "Authorization";

    public static final String BEARER_PREFIX = "Bearer ";

    private Key key; // 시크릿 키를 저장할 Key 타입 변수

    @Value("${jwt.secret}")
    private String secretString; // application.yml에서 읽어올 String 변수

    @Value("${jwt.accessTokenExpiration}")
    private Long accessTokenExpiration;

    @PostConstruct
    public void init() {
        // application.yml의 Base64 문자열 시크릿 키를 올바르게 디코딩하여 Key 객체로 만듦.
        byte[] keyBytes = Decoders.BASE64.decode(secretString);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateAccessToken(String email, UserRole userRole) {
        Date now = new Date(); // 현재 시간
        Date expiry = new Date(now.getTime() + accessTokenExpiration); // 만료 시간
        return Jwts.builder()
                .setSubject(email)
                .claim("role", userRole.name()) // 사용자 역할은 claim에 따로 추가
                .setIssuedAt(now) // 발급 시각
                .setExpiration(expiry) // 만료 시각
                .signWith(this.key, SignatureAlgorithm.HS256) // 미리 초기화된 'this.key' 사용 및 알고리즘 지정
                .compact(); // JWT 문자열 생성
    }

    public boolean isValid(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(this.key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            log.error("유효하지 않은 JWT 토큰입니다. Error: {}", e.getMessage());
            return false;
        }
    }

    public Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(this.key)
                .build()
                .parseClaimsJws(token)
                .getBody(); // Claims: JWT 내부 데이터 (subject, issuedAt, 등등)
    }
}
