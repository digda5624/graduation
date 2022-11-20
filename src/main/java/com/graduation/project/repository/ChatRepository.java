package com.graduation.project.repository;

import com.graduation.project.domain.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    @Query("select c from Chat c " +
            "join fetch c.chatRoom cr " +
            "where cr.id = :roomId " +
            "order by c.createdDate desc")
    List<Chat> findByChatRoom(@Param("roomId") Long roomId);
}
