package com.school21.cinemaspringboot.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cinema_authentications")
@Data
public class Authentication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    @Column(name = "authentication_date")
    private Date date;

    @Column(name = "ip_address")
    private String ipAddress;

    public Authentication() {
    }

    public Authentication(Long id, User user, Date date, String ipAddress) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.ipAddress = ipAddress;
    }

    public Authentication withUser(User user) {
        this.user = user;
        return this;
    }

    public Authentication withDate(Date date) {
        this.date = date;
        return this;
    }

    public Authentication withIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }
}
