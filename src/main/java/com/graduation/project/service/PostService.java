package com.graduation.project.service;

import com.graduation.project.domain.Post;
import com.graduation.project.domain.User;
import com.graduation.project.dto.Result;
import com.graduation.project.dto.SavePostRequest;
import com.graduation.project.dto.UserPostResponse;
import com.graduation.project.error.UserErrorResult;
import com.graduation.project.error.UserException;
import com.graduation.project.repository.PostRepository;
import com.graduation.project.repository.UserRepository;
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
    private final UserRepository userRepository;

    public Result<List<UserPostResponse>> searchByUser(Long userId) {
        List<Post> posts = postRepository.findByUser(userId);
        List<UserPostResponse> collect = posts.stream()
                .map(post -> new UserPostResponse(
                        post.getId(), post.getTitle(), post.getComments().size(), post.getPostHearts().size()
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
}
