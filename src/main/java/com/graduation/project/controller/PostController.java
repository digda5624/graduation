package com.graduation.project.controller;

import com.graduation.project.argumentResolver.Login;
import com.graduation.project.domain.enumtype.PostType;
import com.graduation.project.dto.*;
import com.graduation.project.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/user/post")
    public Result searchPostByUSer(@Login Long userId) {
        return postService.searchByUser(userId);
    }

    @PostMapping("/post")
    public void savePost(@Login Long userId, @RequestBody SavePostRequest request) {
        postService.savePost(userId, request);
    }

    @GetMapping("/post/preview/main")
    public PostPreviews minaPostPreview() {
        return postService.mainPreviewPost();
    }

    @GetMapping("/post/preview/post-type")
    public Slice<PostPreview> postTypePreview(@RequestParam("postType") PostType postType, Pageable pageable) {
        return postService.previewPostByPostType(postType, pageable);
    }

    @GetMapping("/post/detail")
    public PostDetail getPostDetail(@RequestParam("postId") Long postId) {
        return postService.getPostDetail(postId);
    }
}
