package com.graduation.project.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class CommentHeart extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    public static CommentHeart createCommentHeart(User user, Comment comment) {
        CommentHeart commentHeart = new CommentHeart();
        commentHeart.user = user;
        commentHeart.comment = comment;
        return commentHeart;
    }
}
