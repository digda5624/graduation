package com.graduation.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Room {
    @Id @GeneratedValue
    private Long id;

    private Integer price;

    @OneToMany(mappedBy = "room")
    private List<Prefer> prefers = new ArrayList<>();
}
