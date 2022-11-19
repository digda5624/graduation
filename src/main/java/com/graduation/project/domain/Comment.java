package com.graduation.project.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "Comments")
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;          // 게시글 작성 날짜
    private LocalTime time;          // 게시글 작성 시간
    private Boolean anonymous;       // 익명 댓글 여부
    private String content;          // 댓글 내용


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.REMOVE)
    private List<CommentHeart> commentHearts;

    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.REMOVE)
    private List<Comment> subComments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pComment_id")
    private Comment parentComment;

    @Builder
    public Comment(Long id, LocalDate date, LocalTime time, Boolean anonymous, String content, Post post, User user) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.anonymous = anonymous;
        this.content = content;
        this.post = post;
        this.user = user;
    }

    public static Comment createComment(Boolean anonymous, String content, Post post, User user) {
        return Comment.builder()
                .anonymous(anonymous)
                .content(content)
                .post(post)
                .user(user)
                .build();
    }

    public void updateParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }
}
