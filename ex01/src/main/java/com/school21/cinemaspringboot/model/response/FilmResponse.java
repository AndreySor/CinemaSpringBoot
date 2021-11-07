package com.school21.cinemaspringboot.model.response;

import lombok.Data;

@Data
public class FilmResponse {

    private String name;
    private String posterUrl;

    public FilmResponse(String name, String posterUrl) {
        this.name = name;
        this.posterUrl = posterUrl;
    }
}
