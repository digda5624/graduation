package com.graduation.project.domain;

import com.graduation.project.domain.enumtype.PostType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;                   // 개사글 제목
    @Lob
    private String content;                 // 게시글 내용
    private Boolean anonymous;              // 익명
    private PostType postType;              // 게시판 종류
    private Integer imgCnt;                 // 게시글 이미지 개수 최대 __장
    private Integer videoCnt;               // 게시글 동영상 개수 최대 _개

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<PostHeart> postHearts;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    @Builder
    private Post(String title, String content, Boolean anonymous, Integer imgCnt, Integer videoCnt, User user) {
        this.title = title;
        this.content = content;
        this.anonymous = anonymous;
        this.imgCnt = imgCnt;
        this.videoCnt = videoCnt;
        this.user = user;
    }

    public static Post createPost(String title, String content, Boolean anonymous, PostType postType, User user) {
        return Post.builder()
                .title(title)
                .content(content)
                .anonymous(anonymous)
                .postType(postType)
                .user(user)
                .build();
    }

}
