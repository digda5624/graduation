package com.graduation.project.error;

import com.graduation.project.error.exceptionHandler.ErrorResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum PostErrorResult implements ErrorResult {

    POST_NOT_FINE(HttpStatus.BAD_REQUEST, "POST NOT FOUND"),
    POST_NOT_EXIST(HttpStatus.BAD_REQUEST, "잘못된 게시물로의 접근입니다."),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
