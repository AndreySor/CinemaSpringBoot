package com.school21.cinemaspringboot.repository;

import com.school21.cinemaspringboot.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Long> {

    Film findByTitle(String title);
    Film findByFilmId(Long id);
}
