package com.school21.cinemaspringboot.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class SaveFilm {
    private String title;
    private MultipartFile file;
    private Integer releaseYear;
    private Integer ageRestriction;
    private String description;

    public SaveFilm() {
    }

    public SaveFilm(String title) {
        this.title = title;
    }
}
