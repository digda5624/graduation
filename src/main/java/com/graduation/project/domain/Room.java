package com.graduation.project.domain;

import com.graduation.project.domain.enumtype.DealType;
import com.graduation.project.domain.enumtype.RoomType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
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

    @OneToMany(mappedBy = "room")
    private List<Review> reviews = new ArrayList<>();

    @Builder
    public Room(String sigungu, String address, Integer price, DealType dealType, Integer managementFees, RoomType roomType, Boolean hasGarage, Boolean canShortLive, Double distance) {
        this.sigungu = sigungu;
        this.address = address;
        this.price = price;
        this.dealType = dealType;
        this.managementFees = managementFees;
        this.roomType = roomType;
        this.hasGarage = hasGarage;
        this.canShortLive = canShortLive;
        this.distance = distance;
    }
}
