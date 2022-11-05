package com.graduation.project.dto;

import lombok.Data;

@Data
public class SignupRequest {
    private String loginId;
    private String nickname;
    private String password;
    private String passwordCheck;
    private String name;
    private String hint;
    private String answer;
}
