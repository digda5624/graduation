package com.graduation.project.error.exceptionHandler;

import com.graduation.project.error.ChatErrorResult;
import com.graduation.project.error.ChatException;
import com.graduation.project.error.UserErrorResult;
import com.graduation.project.error.UserException;
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
                .body(new ErrorResponse(errorResult.getHttpStatus(), errorResult.getMessage()));
    }

    @ExceptionHandler({ChatException.class})
    public ResponseEntity<ErrorResponse> handleUserException(ChatException ex) {
        log.warn("Chat Exception occur: ", ex);
        return this.makeErrorResponseEntity(ex.getChatErrorResult());
    }
    private ResponseEntity<ErrorResponse> makeErrorResponseEntity(ChatErrorResult errorResult) {
        return ResponseEntity.status(errorResult.getHttpStatus())
                .body(new ErrorResponse(errorResult.getHttpStatus(), errorResult.getMessage()));
    }
}
