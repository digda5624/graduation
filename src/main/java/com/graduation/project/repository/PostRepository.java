package com.graduation.project.repository;

import com.graduation.project.domain.Post;
import com.graduation.project.domain.enumtype.PostType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "select p from Post p " +
            "join fetch p.user u " +
            "join fetch p.comments c " +
            //"join fetch p.postHearts h " +
            "where u.id = :userId")
    List<Post> findByUser(@Param("userId") Long userId);

    @Query(value = "select * from " +
            "(select row_number() over (partition by p.post_type order by p.created_date desc) as ranks, " +
            "p.* from post p) as ranking " +
            "where ranking.ranks <= 3 order by post_type, created_date desc ", nativeQuery = true)
    List<Post> findTop3();

    @Query("select p " +
            "from Post p " +
            "where p.postType =:postType " +
            "order by p.createdDate desc")
    Slice<Post> findPostPreview(@Param("postType") PostType postType, Pageable pageable);

    @Query("select p " +
            "from Post p " +
            "join fetch p.user " +
            "where p.id =:postId")
    Optional<Post> findByIdWithUser(Long postId);

}
