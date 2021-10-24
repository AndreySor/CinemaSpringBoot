package com.school21.cinemaspringboot.repository;


import com.school21.cinemaspringboot.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {

    @Query("SELECT new Session(s.id, s.ticketCost, s.date, f) " +
            "FROM Session s JOIN Film f ON s.film.filmId = f.filmId " +
            "WHERE f.title LIKE :request")
    List<Session> searchByRequest(@Param("request") String request);
}
