package com.graduation.project.service;

import com.graduation.project.domain.Comment;
import com.graduation.project.domain.Post;
import com.graduation.project.domain.User;
import com.graduation.project.dto.Result;
import com.graduation.project.dto.SaveCommentRequest;
import com.graduation.project.dto.UserPostResponse;
import com.graduation.project.error.*;
import com.graduation.project.repository.CommentRepository;
import com.graduation.project.repository.PostRepository;
import com.graduation.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public Result<Collection<UserPostResponse>> searchByUser(Long userId) {
        List<Comment> byUser = commentRepository.findByUser(userId);
        Collection<UserPostResponse> collect = byUser.stream()
                .map(comment -> new UserPostResponse(
                        comment.getPost().getId(), comment.getPost().getTitle(), comment.getPost().getComments().size(), comment.getPost().getPostHearts().size()
                ))
                .collect(Collectors.toList());
        return new Result<>(collect);
    }

    public void saveComment(Long userId, Long postId, Long parentCommentId, SaveCommentRequest request) {
        User findUser = userRepository.findById(userId).orElseThrow(() -> new UserException(UserErrorResult.USER_NOT_FOUND));
        Post findPost = postRepository.findById(postId).orElseThrow(() -> new PostException(PostErrorResult.POST_NOT_FINE));

        Comment comment = request.createComment(findUser, findPost);
        if (parentCommentId != null) {
            Comment parentComment = commentRepository.getById(parentCommentId);
            comment.updateParentComment(parentComment);
        }
        commentRepository.save(comment);
    }

    public void deleteComment(Long userId, Long commentId) {
        Comment findComment = commentRepository.findByIdAndUserId(commentId, userId)
                .orElseThrow(() -> new CommentException(CommentErrorResult.COMMENT_NOT_EXIST));
        commentRepository.delete(findComment);
    }
}
