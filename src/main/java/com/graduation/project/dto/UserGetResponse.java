package com.graduation.project.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class UserGetResponse {

    private final String name;
    private final String nickname;
    private final String loginId;
}
