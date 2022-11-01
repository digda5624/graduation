package com.graduation.project.repository;

import com.graduation.project.domain.Review;
import com.graduation.project.repository.dto.RoomReviewDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("select new com.graduation.project.repository.dto.RoomReviewDTO" +
            "(r.id, r.content, r.score, r.createdDate, u.id, u.name)" +
            "from Review r join r.user as u " +
            "where r.room = :roomId")
    List<RoomReviewDTO> getRoomReviews(@Param("roomId") final Long roomId);

}
