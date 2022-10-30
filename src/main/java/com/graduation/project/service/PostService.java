package com.graduation.project.service;

import com.graduation.project.domain.Post;
import com.graduation.project.dto.Result;
import com.graduation.project.dto.UserPostResponse;
import com.graduation.project.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Result<List<UserPostResponse>> searchByUser(Long userId) {
        List<Post> posts = postRepository.findByUser(userId);
        List<UserPostResponse> collect = posts.stream()
                .map(post -> new UserPostResponse(
                        post.getId(), post.getTitle(), post.getComments().size(), post.getPostHearts().size()
                ))
                .collect(Collectors.toList());
        return new Result<>(collect);
    }
}
