package com.sparta.baro_15.exception;

import com.sparta.baro_15.dto.ResErrorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // CustomException 계열의 예외를 처리
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ResErrorDto> handleCustomException(CustomException e) {
        ResErrorDto response = ResErrorDto.builder()
                .error(ResErrorDto.ErrorDetail.builder()
                        .code(e.getCode())
                        .message(e.getMessage())
                        .build())
                .build();
        return new ResponseEntity<>(response, e.getHttpStatus()); // e.getHttpStatus()를 사용
    }

    // @Valid 어노테이션을 사용한 유효성 검사 실패 시 예외 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResErrorDto> handleValidationExceptions(MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();

        ResErrorDto response = ResErrorDto.builder()
                .error(ResErrorDto.ErrorDetail.builder()
                    .code(ExceptionCode.VALIDATION_FAILED.getCode())
                    .message(errorMessage)
                    .build())
                .build();
        return new ResponseEntity<>(response, ExceptionCode.VALIDATION_FAILED.getHttpStatus());
    }

    // 그 외 예상치 못한 모든 RuntimeException 처리
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResErrorDto> handleRuntimeException(RuntimeException e) {
        System.err.println("Unexpected RuntimeException: " + e.getMessage());
        e.printStackTrace();
        ResErrorDto response = ResErrorDto.builder()
                .error(ResErrorDto.ErrorDetail.builder()
                        .code(ExceptionCode.INTERNAL_SERVER_ERROR.getCode())
                        .message(ExceptionCode.INTERNAL_SERVER_ERROR.getMessage())
                        .build())
                .build();

        return new ResponseEntity<>(response, ExceptionCode.INTERNAL_SERVER_ERROR.getHttpStatus());
    }
}
