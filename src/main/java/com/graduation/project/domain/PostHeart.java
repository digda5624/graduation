package com.graduation.project.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class PostHeart extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    public static PostHeart createPostHeart(User user, Post post) {
        PostHeart postHeart = new PostHeart();
        postHeart.user = user;
        postHeart.post = post;
        return postHeart;
    }
}
