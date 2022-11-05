package com.graduation.project.dto;

import com.graduation.project.domain.User;
import com.graduation.project.domain.enumtype.Auth;
import lombok.Data;

@Data
public class UserInfoResponse {
    private Long id;
    private String nickname;
    private String name;
    private Auth auth;

    public UserInfoResponse(User user) {
        this.id = user.getId();
        this.nickname = user.getNickname();
        this.name = user.getName();
        this.auth = user.getAuth();
    }
}
