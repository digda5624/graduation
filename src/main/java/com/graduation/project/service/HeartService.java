package com.graduation.project.service;

import com.graduation.project.domain.*;
import com.graduation.project.error.*;
import com.graduation.project.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class HeartService {
    private final PostHeartRepository postHeartRepository;
    private final CommentHeartRepository commentHeartRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public void savePostHeart(Long userId, Long postId) {

        Post post = postRepository.findByIdWithPostHeart(postId)
                .orElseThrow(() -> new PostException(PostErrorResult.POST_NOT_FINE));

        post.getPostHearts().stream()
                .filter(ph -> Objects.equals(ph.getUser().getId(), userId))
                .findFirst()
                .ifPresent(ph -> {
                    throw new UserException(UserErrorResult.ALREADY_SAVE_HEART);
                });


        User user = userRepository.getById(userId);
        PostHeart postHeart = PostHeart.createPostHeart(user, post);
        postHeartRepository.save(postHeart);
    }

    public void saveCommentHeart(Long userId, Long commentId) {

        Comment comment = commentRepository.findByIdWithCommentHeart(commentId)
                .orElseThrow(() -> new CommentException(CommentErrorResult.COMMENT_NOT_FIND));

        comment.getCommentHearts().stream()
                .filter(ch -> Objects.equals(ch.getUser().getId(), userId))
                .findFirst()
                .ifPresent(ch -> {throw new UserException(UserErrorResult.ALREADY_SAVE_HEART);});

        User user = userRepository.getById(userId);
        CommentHeart commentHeart = CommentHeart.createCommentHeart(user, comment);
        commentHeartRepository.save(commentHeart);
    }
}
