package com.graduation.project.service;

import com.graduation.project.domain.Comment;
import com.graduation.project.dto.Result;
import com.graduation.project.dto.UserPostResponse;
import com.graduation.project.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private CommentRepository commentRepository;

    public Result<Collection<UserPostResponse>> searchByUser(Long userId) {
        List<Comment> byUser = commentRepository.findByUser(userId);
        Collection<UserPostResponse> collect = byUser.stream()
                .map(comment -> new UserPostResponse(
                        comment.getPost().getId(), comment.getPost().getTitle(), comment.getPost().getComments().size(), comment.getPost().getPostHearts().size()
                ))
                .collect(Collectors.toList());
        return new Result<>(collect);
    }
}
