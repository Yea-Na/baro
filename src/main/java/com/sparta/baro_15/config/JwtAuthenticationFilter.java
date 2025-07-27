package com.sparta.baro_15.config;


import com.sparta.baro_15.exception.CustomException;
import com.sparta.baro_15.exception.ExceptionCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

            String authHeader = request.getHeader("Authorization");

            // 0. 로그인, 회원가입 경로는 JWT 검사 없이 무조건 통과
            String path = request.getRequestURI();

            if (path.equals("/v1/api/auth/signup") || path.equals("/v1/api/auth/signin")) {
                filterChain.doFilter(request, response);
                return;
            }

            // 1. 헤더가 없거나 Bearer가 아닌 경우 -> 다음 필터로 넘김
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }

            // 2. JWT 추출
            String token = authHeader.substring(7); // "Bearer " 이후 토큰

            // 3. 토큰 유효성 검사
            if (!jwtUtil.isValid(token)) {
                throw new CustomException(ExceptionCode.UNAUTHORIZED_ACCESS);
            }

            // 4. 토큰에서 이메일, 역할 꺼내기
            Claims claims;
            try {
                claims = jwtUtil.parseToken(token);
            } catch (JwtException e) {
                throw new CustomException(ExceptionCode.INTERNAL_SERVER_ERROR);
            }
            String email = claims.getSubject();
            String role = claims.get("role", String.class);

            ////역할 GrantedAuthority 리스트 형으로 만들기
            List<GrantedAuthority> authorities =
                    List.of(new SimpleGrantedAuthority("ROLE_" + role));

            // 5. 인증 객체 생성 및 등록
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            email, null, authorities
                    );

            ////IP 주소, 세션 ID, 브라우저 정보 등 웹 요청의 메타데이터 자동입력(부가정보)
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            ////사용자 인증 완료
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            // 6. 다음 필터 진행
            filterChain.doFilter(request, response);
        }
}

