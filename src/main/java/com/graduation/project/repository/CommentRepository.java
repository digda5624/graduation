package com.graduation.project.repository;

import com.graduation.project.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select c from Comment c " +
            "join fetch c.post p " +
            "join fetch p.postHearts h " +
            "join fetch c.user u " +
            "where u.id = :userId")
    List<Comment> findByUser(@Param("userId") Long userId);

    @Query("select distinct c " +
            "from Comment c " +
            "left join fetch c.commentHearts ch " +
            "where c.id =:commentId")
    Optional<Comment> findByIdWithCommentHeart(@Param("commentId") Long commentId);

    @Query("select c " +
            "from Comment c " +
            "where c.id =:commentId " +
            "and c.user.id =:userId")
    Optional<Comment> findByIdAndUserId(@Param("commentId") Long commentId, @Param("userId") Long userId);
}
