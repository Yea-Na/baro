package com.sparta.baro_15.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResTokenDto {

    private String accessToken;
    private String refreshToken;

    public ResTokenDto(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
