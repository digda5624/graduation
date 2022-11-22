package com.graduation.project.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class UserPostResponse {

    private final Long postId;
    private final String title;
    private final String postType;
    private final Integer commentCnt;
    private final Integer heartCnt;
    private final LocalDate createdDate;
    private final LocalTime createdTime;
}
