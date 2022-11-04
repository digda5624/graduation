package com.graduation.project.repository;

import com.graduation.project.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "select p from Post p " +
            "join fetch p.user u " +
            "join fetch p.comments c " +
            //"join fetch p.postHearts h " +
            "where u.id = :userId")
    List<Post> findByUser(@Param("userId") Long userId);
}
