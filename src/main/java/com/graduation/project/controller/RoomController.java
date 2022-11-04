package com.graduation.project.controller;

import com.graduation.project.dto.RoomIdsRequest;
import com.graduation.project.dto.RoomSearchRequest;
import com.graduation.project.dto.RoomSearchResponse;
import com.graduation.project.repository.dto.RoomInfo;
import com.graduation.project.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/room/map")
    @ResponseStatus(HttpStatus.OK)
    public List<Long> roomSearchForMap(@ModelAttribute RoomSearchRequest request){
        return roomService.search(request);
    }

    @PostMapping("find/rooms")
    @ResponseStatus(HttpStatus.OK)
    public Slice<RoomSearchResponse> roomList(Pageable pageable, @RequestBody RoomIdsRequest request){
        return roomService.getRooms(request.getRoomIds(), pageable);
    }

    @GetMapping("/room")
    @ResponseStatus(HttpStatus.OK)
    public RoomInfo getRoomInfo(@RequestParam Long roomId){
        return roomService.getRoomInfo(roomId);
    }

}
