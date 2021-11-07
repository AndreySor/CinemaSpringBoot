package com.school21.cinemaspringboot.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cinema_messages")
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "message")
    private String message;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User user;

    @Column(name = "message_date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;

    @Transient
    private MessageType type;

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }

    public Message() {
    }

    public Message(Long id, String message, User user, Date date, Film film) {
        this.id = id;
        this.message = message;
        this.user = user;
        this.date = date;
        this.film = film;
    }
}
