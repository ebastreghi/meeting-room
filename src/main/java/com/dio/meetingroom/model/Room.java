package com.dio.meetingroom.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "meetingroom")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "date", nullable = false)
    private String date;

    @Column(name = "starHour", nullable = false)
    private String starHour;

    @Column(name = "endHour", nullable = false)
    private String endHour;

}
