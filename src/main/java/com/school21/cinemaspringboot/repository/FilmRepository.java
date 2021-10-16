package com.school21.cinemaspringboot.repository;


import com.school21.cinemaspringboot.model.Film;

public interface FilmRepository extends BaseCRUDRepository<Film> {

    Film getByTitle(String title);
}
