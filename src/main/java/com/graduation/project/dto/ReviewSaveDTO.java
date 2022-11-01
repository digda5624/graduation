package com.graduation.project.dto;

import com.graduation.project.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewSaveDTO {

    private String content;
    private Integer score;

    private Long userId;
    private Long roomId;

}
