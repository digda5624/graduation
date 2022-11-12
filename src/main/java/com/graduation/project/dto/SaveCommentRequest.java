package com.graduation.project.dto;

import com.graduation.project.domain.Comment;
import com.graduation.project.domain.Post;
import com.graduation.project.domain.User;
import lombok.Data;

@Data
public class SaveCommentRequest {
    private String content;
    private Boolean anonymous;

    public Comment createComment(User findUser, Post findPost) {
        return Comment.createComment(this.anonymous, this.content, findPost, findUser);
    }
}
