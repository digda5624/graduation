package com.graduation.project.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Prefer extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    private LocalDateTime dateTime;             // 찜한 시간
}
