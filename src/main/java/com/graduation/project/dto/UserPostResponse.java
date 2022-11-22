package com.graduation.project.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class UserPostResponse {

    private final Long id;
    private final String title;
    private final String postType;
    private final Integer commentCnt;
    private final Integer heartCnt;
}
