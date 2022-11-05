package com.graduation.project.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@RequiredArgsConstructor
@Getter
public class TokenPair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accessToken;
    private String refreshToken;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    public static TokenPair createTokenPair(String accessToken, String refreshToken, User user) {
        TokenPair tokenPair = new TokenPair();
        tokenPair.accessToken = accessToken;
        tokenPair.refreshToken = refreshToken;
        tokenPair.user = user;
        return tokenPair;
    }

    public void updateToken(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
