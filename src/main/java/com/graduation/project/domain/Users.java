package com.graduation.project.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
public class Users {
    @Id @GeneratedValue
    private Long id;

    private String loginId;
    private String nickname;
    private String password;
    private String hint;
    private String name;
    private String answer;

    @OneToMany(mappedBy = "user")
    private List<Prefer> prefers = new ArrayList<>();
}
