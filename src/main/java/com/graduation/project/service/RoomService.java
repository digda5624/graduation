package com.graduation.project.service;

import com.graduation.project.dto.RoomSearchRequest;
import com.graduation.project.dto.RoomSearchResponse;
import com.graduation.project.repository.DynamicQuery;
import com.graduation.project.repository.RoomRepository;
import com.graduation.project.repository.dto.RoomInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final DynamicQuery dynamicQuery;

    public List<Long> search(RoomSearchRequest request) {
        return dynamicQuery.searchRoom(request);
    }

    public Slice<RoomSearchResponse> getRooms(Set<Long> roomIds, Pageable pageable) {
        return roomRepository.findByIdSet(roomIds, pageable)
                .map(RoomSearchResponse::new);
    }

    public RoomInfo getRoomInfo(Long roomId) {
        return roomRepository.findRoomInfoById(roomId);
    }
}
