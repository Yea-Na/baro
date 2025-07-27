package com.sparta.baro_15.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI openAPI() {
        // 보안 스키마 정의 (JWT 토큰을 위한 Bearer 인증 방식)
        SecurityScheme securityScheme = new SecurityScheme()
                .type(Type.HTTP) // HTTP 방식
                .scheme("bearer")              // scheme: bearer
                .bearerFormat("JWT")           // bearer format: JWT
                .in(In.HEADER)  // 헤더를 통해 전달
                .name("Authorization");        // 헤더 이름: Authorization

        // 보안 요구사항 추가 (모든 API에 Authorization 헤더 필요하다고 명시)
        SecurityRequirement securityRequirement = new SecurityRequirement().addList("bearerAuth");

        return new OpenAPI()
                .components(new Components().addSecuritySchemes("bearerAuth", securityScheme))
                .addSecurityItem(securityRequirement)
                .info(new Info()
                        .title("Baro 서비스 API 문서") // 서비스의 제목
                        .description("스웨거 API 문서입니다.") // 서비스 설명
                        .version("1.0.0")); // API 버전
    }

}
