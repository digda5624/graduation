package com.graduation.project.dto;

import com.graduation.project.domain.Room;
import com.graduation.project.domain.enumType.DealType;
import com.graduation.project.domain.enumType.RoomType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoomSearchResponse {

    private Long id;
    private String sigungu;
    private String address;
    private Integer price;
    private DealType dealType;
    private Integer managementFees;
    private RoomType roomType;
    private Boolean hasGarage;
    private Boolean canShortLive;
    private Double distance;

    public RoomSearchResponse(Room room) {
        id = room.getId();
        sigungu = room.getSigungu();
        address = room.getAddress();
        price = room.getPrice();
        dealType = room.getDealType();
        managementFees = room.getManagementFees();
        roomType = room.getRoomType();
        hasGarage = room.getHasGarage();
        canShortLive = room.getCanShortLive();
        distance = room.getDistance();
    }
}
