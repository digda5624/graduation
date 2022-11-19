package com.graduation.project.controller;

import com.graduation.project.argumentResolver.Login;
import com.graduation.project.service.HeartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HeartController {
    private final HeartService heartService;

    @PostMapping("/heart/post")
    public void savePostHeart(@Login Long userId, @RequestParam("postId") Long postId) {
        heartService.savePostHeart(userId, postId);
    }

    @PostMapping("/heart/comment")
    public void saveCommentHeart(@Login Long userId, @RequestParam("commentId") Long commentId) {
        heartService.saveCommentHeart(userId, commentId);
    }
}
