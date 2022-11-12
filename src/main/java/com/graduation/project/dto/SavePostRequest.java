package com.graduation.project.dto;

import com.graduation.project.domain.Post;
import com.graduation.project.domain.User;
import com.graduation.project.domain.enumtype.PostType;
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
    private PostType postType;

    public Post createPost(User user) {
        return Post.createPost(title, content, anonymous, postType, user);
    }
}
