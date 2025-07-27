package com.sparta.baro_15.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResErrorDto {

    private ErrorDetail error;

    @Getter
    @Builder
    public static class ErrorDetail {
        private String code;
        private String message;
    }
}
