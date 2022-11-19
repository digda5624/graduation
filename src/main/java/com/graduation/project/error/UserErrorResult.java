package com.graduation.project.error;

import com.graduation.project.error.exceptionHandler.ErrorResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorResult implements ErrorResult {

    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "USER NOT FOUND"),
    ALREADY_LOGINID_EXIST(HttpStatus.BAD_REQUEST, "이미 존재하는 아이디입니다."),
    ALREADY_NICKNAME_EXIST(HttpStatus.BAD_REQUEST, "이미 존재하는 닉네임입니다."),
    ALREADY_LOGINID_OR_NICKNAME_EXIST(HttpStatus.BAD_REQUEST, "이미 존재하는 아이디 또는 닉네임입니다."),
    NOT_MATCH_PASSWORD_CHECK(HttpStatus.BAD_REQUEST, "비밀번호확인이 올바르지 않습니다."),
    ALREADY_SAVE_HEART(HttpStatus.BAD_REQUEST, "이미 좋아요한 컨텐츠입니다.")
    ;
    private final HttpStatus httpStatus;
    private final String message;
}
