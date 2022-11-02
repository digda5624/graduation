package com.graduation.project.repository;

import com.graduation.project.domain.Room;
import com.graduation.project.repository.dto.RoomInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("select r from Room r where r.id in :roomIds")
    Slice<Room> findByIdSet(Set<Long> roomIds, Pageable pageable);

    @Query("select new com.graduation.project.repository.dto.RoomInfo(r, avg(re.score)) " +
            "from Room r " +
            "left join r.reviews as re " +
            "where r.id = :roomId " +
            "group by r.id")
    RoomInfo findRoomInfoById(Long roomId);
}
