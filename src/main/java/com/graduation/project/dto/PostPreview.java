package com.graduation.project.dto;

import com.graduation.project.domain.Post;
import com.graduation.project.domain.enumtype.PostType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class PostPreview{
    private String title;
    private String content;
    private Boolean anonymous;
    private LocalDateTime createdDate;
    private Integer commentCnt;
    private Integer heartCnt;
    private PostType postType;

    public static PostPreview createPostPreview(Post post) {
        return PostPreview.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .anonymous(post.getAnonymous())
                .createdDate(post.getCreatedDate())
                .commentCnt(post.getComments().size())
                .heartCnt(post.getPostHearts().size())
                .postType(post.getPostType())
                .build();
    }
}