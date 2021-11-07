package com.school21.cinemaspringboot.repository;

import com.school21.cinemaspringboot.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("FROM Message WHERE film.filmId =:filmId ORDER BY film.filmId ASC")
    List<Message> getLastTwelveMessagesFromFilmId(@Param("filmId") Long filmId);
}
