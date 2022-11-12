package com.graduation.project.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostException extends RuntimeException{
    private final PostErrorResult errorResult;
}
