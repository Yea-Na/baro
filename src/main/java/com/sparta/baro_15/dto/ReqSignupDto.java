package com.sparta.baro_15.dto;

import com.sparta.baro_15.domain.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReqSignupDto {

    @NotBlank(message = "비밀번호는 필수입니다")
    private String password;

    @NotBlank(message = "유저명은 필수입니다")
    private String username;

    @Email(message = "유효한 이메일 형식이 아닙니다")
    @NotBlank(message = "이메일은 필수입니다")
    private String email;

    private UserRole role;
    private String adminToken;

    public ReqSignupDto(String password, String username, String email, UserRole role,
            String adminToken) {
        this.password = password;
        this.username = username;
        this.email = email;
        this.role = role;
        this.adminToken = adminToken;
    }
}
