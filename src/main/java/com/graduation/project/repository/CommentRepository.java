package com.graduation.project.repository;

import com.graduation.project.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Id;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select c from Comment c " +
            "join fetch c.post p " +
            "join fetch p.postHearts h " +
            "join fetch c.user u " +
            "where u.id = :userId")
    List<Comment> findByUser(@Param("userId") Long userId);
}
