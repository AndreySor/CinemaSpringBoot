package com.school21.cinemaspringboot.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class SessionsResponse {

    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private Date dateTime;
    private FilmResponse film;

    public SessionsResponse() {
    }

    public SessionsResponse(Long id, Date dateTime, FilmResponse film) {
        this.id = id;
        this.dateTime = dateTime;
        this.film = film;
    }
}
