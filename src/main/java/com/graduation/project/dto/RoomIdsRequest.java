package com.graduation.project.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Data
public class RoomIdsRequest {

    Set<Long> roomIds = new HashSet<>();
}
