package com.graduation.project.error;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({UserException.class})
    public ResponseEntity<ErrorResponse> handleUserException(UserException ex) {
        log.warn("User Exception occur: ", ex);
        return this.makeErrorResponseEntity(ex.getErrorResult());
    }
    private ResponseEntity<ErrorResponse> makeErrorResponseEntity(UserErrorResult errorResult) {
        return ResponseEntity.status(errorResult.getHttpStatus())
                .body(new ErrorResponse(errorResult.name(), errorResult.getMessage()));
    }

    @Data
    @RequiredArgsConstructor
    static class ErrorResponse {
        private final String code;
        private final String message;
    }

}
