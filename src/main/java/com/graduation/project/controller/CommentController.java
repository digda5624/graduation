package com.graduation.project.controller;

import com.graduation.project.argumentResolver.Login;
import com.graduation.project.dto.Result;
import com.graduation.project.dto.SaveCommentRequest;
import com.graduation.project.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/user/comment/{userId}")
    public Result searchCommentByUser(@PathVariable Long userId) {
        return commentService.searchByUser(userId);
    }

    @PostMapping("/comment")
    public void saveComment(@Login Long userId,
                            @RequestParam("postId") Long postId,
                            @RequestParam(value = "commentId", required = false) Long commentId,
                            @RequestBody SaveCommentRequest request) {
        commentService.saveComment(userId, postId, commentId, request);
    }
}
