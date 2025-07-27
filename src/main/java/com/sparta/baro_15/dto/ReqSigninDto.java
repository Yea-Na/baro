package com.sparta.baro_15.dto;

import com.sparta.baro_15.domain.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReqSigninDto {

    @NotBlank(message = "비밀번호는 필수입니다")
    private String password;

    @NotBlank(message = "유저명은 필수입니다")
    private String username;

    public ReqSigninDto(String password, String username) {
        this.password = password;
        this.username = username;
    }
}
