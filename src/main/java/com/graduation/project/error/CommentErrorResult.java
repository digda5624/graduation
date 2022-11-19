package com.graduation.project.error;

import com.graduation.project.error.exceptionHandler.ErrorResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommentErrorResult implements ErrorResult {

    COMMENT_NOT_FIND(HttpStatus.BAD_REQUEST, "해당 댓글을 찾을 수 없습니다."),
    COMMENT_NOT_EXIST(HttpStatus.BAD_REQUEST, "잘못된 댓글로의 접근입니다."),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
