package com.graduation.project.error.exceptionHandler;

import org.springframework.http.HttpStatus;

public interface ErrorResult {
    HttpStatus getHttpStatus();
    String getMessage();
}
