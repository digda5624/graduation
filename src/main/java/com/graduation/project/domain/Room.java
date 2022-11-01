package com.graduation.project.domain;

import com.graduation.project.domain.enumType.DealType;
import com.graduation.project.domain.enumType.RoomType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sigungu;

    private String address;
    private Integer price;
    @Enumerated(EnumType.STRING)
    private DealType dealType;
    private Integer managementFees;
    @Enumerated(EnumType.STRING)
    private RoomType roomType;
    private Boolean hasGarage;
    private Boolean canShortLive;
    private Double distance;

    @OneToMany(mappedBy = "room")
    private List<Prefer> prefers = new ArrayList<>();
}
