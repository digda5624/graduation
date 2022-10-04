package com.graduation.project.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Post extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String title;                   // 개사글 제목
    @Lob
    private String content;                 // 게시글 내용
    private Boolean anonymous;              // 익명
    private Integer commentCnt;             //댓글 수
    private Integer heartCnt;               //좋아요 수
    private Integer imgCnt;                 // 게시글 이미지 개수 최대 __장
    private Integer videoCnt;               // 게시글 동영상 개수 최대 _개

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    @OneToMany(mappedBy = "post")
    private List<PostHeart> postHearts;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;
}
