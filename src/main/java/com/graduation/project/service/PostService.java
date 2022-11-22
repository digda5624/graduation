package com.graduation.project.service;

import com.graduation.project.domain.Post;
import com.graduation.project.domain.User;
import com.graduation.project.domain.enumtype.PostType;
import com.graduation.project.dto.*;
import com.graduation.project.error.PostErrorResult;
import com.graduation.project.error.PostException;
import com.graduation.project.error.UserErrorResult;
import com.graduation.project.error.UserException;
import com.graduation.project.repository.PostRepository;
import com.graduation.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Result<List<UserPostResponse>> searchByUser(Long userId) {
        List<Post> posts = postRepository.findByUser(userId);
        List<UserPostResponse> collect = posts.stream()
                .map(post -> new UserPostResponse(
                        post.getId(), post.getTitle(), post.getPostType().getName(), 
                    post.getComments().size(), post.getPostHearts().size(),
                    post.getCreatedDate().toLocalDate(), post.getCreatedDate().toLocalTime()
                ))
                .collect(Collectors.toList());
        return new Result<>(collect);
    }

    public void savePost(Long userId, SavePostRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorResult.USER_NOT_FOUND));
        Post post = request.createPost(user);
        postRepository.save(post);
    }

    public PostPreviews mainPreviewPost() {
        List<Post> posts = postRepository.findTop3();
        for (Post post : posts) {
            System.out.println(post.getTitle());
        }
        Map<String, List<PostPreview>> map = posts.stream()
                .map(PostPreview::createPostPreview)
                .collect(Collectors.groupingBy(post -> post.getPostType().getName()));
        return new PostPreviews(map);
    }

    public Slice<PostPreview> previewPostByPostType(PostType postType, Pageable pageable) {
        Slice<Post> postSlice = postRepository.findPostPreview(postType, pageable);
        return postSlice.map(PostPreview::createPostPreviewWithUser);
    }

    public PostDetail getPostDetail(Long postId) {
        Post findPost = postRepository.findById(postId).orElseThrow(() -> new PostException(PostErrorResult.POST_NOT_FINE));
        PostDetail postDetail = new PostDetail(findPost);
        return postDetail;
    }

    public void deletePost(Long userId, Long postId) {
        Post findPost = postRepository.findByIdAndUserId(postId, userId)
                .orElseThrow(() -> new PostException(PostErrorResult.POST_NOT_EXIST));
        postRepository.delete(findPost);
    }
}
