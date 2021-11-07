package com.school21.cinemaspringboot.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "cinema_films")
@Data
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Long filmId;

    @Column(name = "title")
    private String title;

    @Column(name = "release_year")
    private Integer releaseYear;

    @Column(name = "age_restrictions")
    private Integer ageRestriction;

    @Column(name = "description")
    private String description;

    @Column(name = "poster")
    private String poster;

    public Film() {
    }

    public Film(String title, Integer releaseYear, Integer ageRestriction, String description, String poster) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.ageRestriction = ageRestriction;
        this.description = description;
        this.poster = poster;
    }

    public Film withId(Long id) {
        this.filmId = id;
        return this;
    }
}
