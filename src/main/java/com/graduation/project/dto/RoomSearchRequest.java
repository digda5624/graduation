package com.graduation.project.dto;

import com.graduation.project.domain.enumType.DealType;
import com.graduation.project.domain.enumType.RoomType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomSearchRequest {

    private DealType dealType;
    private Integer sManagementFees;
    private Integer eManagementFees;
    private RoomType roomType;
    private Double sDistance;
    private Double eDistance;
    private String searchContent;

}
