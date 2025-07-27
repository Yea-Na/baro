package com.sparta.baro_15.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {
    private final ExceptionCode exceptionCode;

    public CustomException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }

    public HttpStatus getHttpStatus() {return this.exceptionCode.getHttpStatus();}

    public String getCode() {return this.exceptionCode.getCode();}

    public String getMessage() {return this.exceptionCode.getMessage();}
}
