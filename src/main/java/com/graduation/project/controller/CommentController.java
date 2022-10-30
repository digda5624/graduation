package com.graduation.project.controller;

import com.graduation.project.dto.Result;
import com.graduation.project.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private CommentService commentService;

    @GetMapping("/user/comment/{userId}")
    public Result searchCommentByUser(@PathVariable Long userId) {
        return commentService.searchByUser(userId);
    }
}
