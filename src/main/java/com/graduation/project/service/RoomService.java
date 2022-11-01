package com.graduation.project.service;

import com.graduation.project.dto.RoomSearchRequest;
import com.graduation.project.repository.DynamicQuery;
import com.graduation.project.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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

//    public void getRooms(Set<Long> roomIds, Pageable pageable) {
//        roomRepository.findBy
//    }
}
