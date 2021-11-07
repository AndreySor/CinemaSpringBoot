package com.school21.cinemaspringboot.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cinema_sessions")
@Data
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ticket_cost")
    private Integer ticketCost;

    @Column(name = "session_date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "hall_id")
    private Hall hall;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;

    public Session() {
    }

    public Session(Long id, Integer ticketCost, Date date, Film film) {
        this.id = id;
        this.ticketCost = ticketCost;
        this.date = date;
        this.film = film;
    }
}
