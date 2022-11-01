package com.graduation.project.repository;

import com.graduation.project.domain.Room;
import com.graduation.project.dto.RoomSearchRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoomRepository extends JpaRepository<Room, Long> {

}
