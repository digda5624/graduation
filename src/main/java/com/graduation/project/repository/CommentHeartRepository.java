package com.graduation.project.repository;

import com.graduation.project.domain.CommentHeart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentHeartRepository extends JpaRepository<CommentHeart, Long> {
}
