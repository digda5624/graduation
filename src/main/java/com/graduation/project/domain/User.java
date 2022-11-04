package com.graduation.project.domain;

import com.graduation.project.domain.enumtype.Auth;
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
}
