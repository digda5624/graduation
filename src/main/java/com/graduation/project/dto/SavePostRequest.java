package com.graduation.project.dto;

import com.graduation.project.domain.Post;
import com.graduation.project.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SavePostRequest {
    private String title;
    private String content;
    private Boolean anonymous;

    public Post createPost(User user) {
        return Post.createPost(title, content, anonymous, user);
    }
}
