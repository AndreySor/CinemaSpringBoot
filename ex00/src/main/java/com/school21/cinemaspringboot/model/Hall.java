package com.school21.cinemaspringboot.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cinema_halls")
@Data
public class Hall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hall_id")
    private Long hallId;

    @Column(name = "serial_number")
    private Integer serialNumber;

    @Column(name = "seats_number")
    private Integer seatsNumber;

    public Hall() {
    }

    public Hall(Long hallId, Integer serialNumber, Integer seatsNumber) {
        this.hallId = hallId;
        this.serialNumber = serialNumber;
        this.seatsNumber = seatsNumber;
    }

    public Hall withId(Long id) {
        this.hallId = id;
        return this;
    }
}
