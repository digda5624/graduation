package com.graduation.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseEntity{
    @Id @GeneratedValue
    private Long id;
    @Lob
    private String content;             // 리뷰 내용
    private LocalDate createDate;       // 리뷰 작성 날짜
    private LocalTime createTime;       // 리뷰 작성 시간
    private LocalDate updateDate;      // 업데이트 날짜
    private LocalTime updateTime;      // 업데이트 날짜g
    private Integer score;              // 리뷰 별점 (1,2,3,4,5 점)
    private Boolean anonymous;          // 리뷰 익명 여부
    @Lob
    private String answer;              // 시설 관계자의 답글 무조건 1개
    private LocalDate answerCreateDate;
    private LocalTime answerCreateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
