package com.graduation.project.controller;

import com.graduation.project.dto.RoomSearchRequest;
import com.graduation.project.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    public List<Long> roomSearchForMap(@ModelAttribute RoomSearchRequest request){
        return roomService.search(request);
    }

    public void roomList(Pageable pageable, @RequestBody Set<Long> roomIds){
        roomService.getRooms(roomIds, pageable);
    }

}
