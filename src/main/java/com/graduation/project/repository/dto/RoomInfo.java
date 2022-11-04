package com.graduation.project.repository.dto;

import com.graduation.project.domain.Room;
import com.graduation.project.dto.RoomSearchResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomInfo {

    private RoomSearchResponse roomInfo;
    private Double avgScore;

    public RoomInfo(Room room, Double avgScore) {
        this.roomInfo = new RoomSearchResponse(room);
        this.avgScore = avgScore;
    }
}
