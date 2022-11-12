package com.graduation.project.dto;

import com.graduation.project.domain.Comment;
import com.graduation.project.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDetail {
    private Long id;
    private Long writer_id;
    private String nickname;
    private Boolean anonymous;
    private LocalDate createdDate;
    private LocalTime createdTime;
    private String title;
    private String content;
    private Integer heartCnt;
    private Integer commentCnt;
    private String postType;
    private List<CommentDetail> comments = new ArrayList<>();

    public PostDetail(Post post) {
        this.id = post.getId();
        this.writer_id = post.getUser().getId();
        if (!post.getAnonymous()) {
            this.anonymous = false;
            this.nickname = post.getUser().getNickname();
        } else{
            this.anonymous = true;
        }
        this.createdDate = post.getCreatedDate().toLocalDate();
        this.createdTime = post.getCreatedDate().toLocalTime();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.commentCnt = post.getComments().size();
        this.heartCnt = post.getPostHearts().size();
        this.postType = post.getPostType().getName();
        this.comments = post.getComments().stream()
                .filter(c -> c.getParentComment() == null)
                .map(CommentDetail::new)
                .collect(Collectors.toList());
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommentDetail {
        private Long id;
        private Long writer_id;
        private String nickname;
        private String content;
        private LocalDate createdDate;
        private LocalTime createdTime;
        private Boolean anonymous;
        private List<CommentDetail> subComments = new ArrayList<>();

        public CommentDetail(Comment comment) {
            this.id = comment.getId();
            this.writer_id = comment.getUser().getId();
            if (!comment.getAnonymous()){
                this.anonymous = false;
                this.nickname = comment.getUser().getNickname();
            } else{
                this.anonymous = true;
            }
            this.content = comment.getContent();
            this.createdDate = comment.getCreatedDate().toLocalDate();
            this.createdTime = comment.getCreatedDate().toLocalTime();
            this.subComments = comment.getSubComments().stream()
                    .map(CommentDetail::new)
                    .collect(Collectors.toList());

        }
    }
}
