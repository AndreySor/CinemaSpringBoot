package com.school21.cinemaspringboot.service;

import com.school21.cinemaspringboot.model.Film;
import com.school21.cinemaspringboot.model.SaveFilm;

import java.util.List;

public interface FilmService {

    void saveFilm(SaveFilm saveFilm);
    void updateFilm(SaveFilm saveFilm);
    List<Film> getAll();
}
