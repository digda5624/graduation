package com.graduation.project.controller;

import com.graduation.project.dto.ReviewSaveDTO;
import com.graduation.project.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    public Long register(@RequestBody ReviewSaveDTO request){
        return reviewService.register(request);
    }

    public Map<String, Object> getRoomReviews(@RequestParam Long roomId){
        return reviewService.getRoomReviews(roomId);
    }

}
