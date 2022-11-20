package com.graduation.project.dto;

import lombok.Data;

@Data
public class CreateChatRoomRequest {
    private String message;
    private Long room_id;
}
