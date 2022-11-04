package com.graduation.project.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class RoomReviewDTO {

    private Long roomId;
    private String content;
    private Integer score;
    private LocalDateTime createDate;

    private Long userId;
    private String userName;

}
