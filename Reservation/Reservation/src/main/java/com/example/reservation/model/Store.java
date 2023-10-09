package com.example.reservation.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private String ownerName;

    @OneToMany(mappedBy = "store")
    private List<Reservation> reservations;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner; // 점장 정보



}