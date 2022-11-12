package com.graduation.project.domain;

import com.graduation.project.domain.enumtype.Auth;
import com.graduation.project.dto.FindPasswordRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Users")
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String loginId;
    private String nickname;
    private String password;
    private String hint;
    private String name;
    private String answer;

    @Enumerated(EnumType.STRING)
    private Auth auth;

    @OneToMany(mappedBy = "user")
    private List<Prefer> prefers = new ArrayList<>();

    @Builder
    public User(String loginId, String nickname, String password, String hint, String name, String answer, Auth auth) {
        this.loginId = loginId;
        this.nickname = nickname;
        this.password = password;
        this.hint = hint;
        this.name = name;
        this.answer = answer;
        this.auth = auth;
    }

    public static User createUser(String loginId, String nickname, String password, String hint, String name, String answer, Auth auth) {
        return builder().loginId(loginId)
                .nickname(nickname)
                .password(password)
                .hint(hint)
                .name(name)
                .answer(answer)
                .auth(auth)
                .build();
    }

    public void changePassword(String password, String newHint, String newAnswer) {
        this.password = password;
        this.hint = newHint;
        this.answer = newAnswer;
    }
}
