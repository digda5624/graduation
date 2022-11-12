package com.graduation.project.controller;

import com.graduation.project.argumentResolver.Login;
import com.graduation.project.dto.Result;
import com.graduation.project.dto.SavePostRequest;
import com.graduation.project.dto.UserPostResponse;
import com.graduation.project.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/user/post/{userId}")
    public Result searchPostByUSer(@PathVariable Long userId) {
        return postService.searchByUser(userId);
    }

    @PostMapping("/user/post")
    public void savePost(@Login Long userId, @RequestBody SavePostRequest request) {
        postService.savePost(userId, request);
    }
}
