package com.graduation.project.dto;

import lombok.Data;

@Data
public class FindPasswordRequest {
    private String password;
    private String passwordCheck;
    private String newHint;
    private String newAnswer;
}
