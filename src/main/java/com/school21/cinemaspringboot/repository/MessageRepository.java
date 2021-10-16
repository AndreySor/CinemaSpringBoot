package com.school21.cinemaspringboot.repository;


import com.school21.cinemaspringboot.model.Message;

import java.util.List;

public interface MessageRepository extends BaseCRUDRepository<Message>{

    List<Message> getLastTwelveMessagesFromFilmId(Long filmId);
}
