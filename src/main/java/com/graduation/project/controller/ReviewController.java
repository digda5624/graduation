package com.graduation.project.controller;

import com.graduation.project.dto.ReviewSaveDTO;
import com.graduation.project.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/review")
    public Long register(@RequestBody ReviewSaveDTO request){
        return reviewService.register(request);
    }

    @GetMapping("/review")
    public Map<String, Object> getRoomReviews(@RequestParam Long roomId){
        return reviewService.getRoomReviews(roomId);
    }

}
