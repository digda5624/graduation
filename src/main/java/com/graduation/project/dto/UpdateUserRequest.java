package com.graduation.project.dto;


import lombok.Data;

@Data
public class UpdateUserRequest {

    private String password;
    private String nickname;
    private String loginId;
}
