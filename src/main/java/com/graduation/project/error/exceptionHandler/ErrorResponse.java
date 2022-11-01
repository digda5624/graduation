package com.graduation.project.error.exceptionHandler;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@RequiredArgsConstructor
public class ErrorResponse {
    private final HttpStatus code;
    private final String message;

    public ErrorResponse(ErrorResult errorResult) {
        this.code = errorResult.getHttpStatus();
        this.message = errorResult.getMessage();
    }
}