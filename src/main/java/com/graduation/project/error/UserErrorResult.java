package com.graduation.project.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorResult {

    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "USER NOT FOUND"),
    ;
    private final HttpStatus httpStatus;
    private final String message;
}
