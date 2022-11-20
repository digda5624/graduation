package com.graduation.project.repository;

import com.graduation.project.domain.ChatRoom;
import com.graduation.project.domain.Post;
import com.graduation.project.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    Optional<ChatRoom> findByReceiverAndSenderAndPost(User receiver, User sender, Post post);

    @Query("select cr from ChatRoom cr " +
            "left join fetch cr.post p " +
            "where cr.receiver.id = :userId or cr.sender.id = :userId order by cr.updatedDate desc ")
    List<ChatRoom> findByUser(@Param("userId") Long userId);
}
