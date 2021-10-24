package com.school21.cinemaspringboot.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cinema_sessions")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTicketCost() {
        return ticketCost;
    }

    public void setTicketCost(Integer ticketCost) {
        this.ticketCost = ticketCost;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
