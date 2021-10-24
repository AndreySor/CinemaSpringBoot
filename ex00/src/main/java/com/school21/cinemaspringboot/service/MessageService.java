package com.school21.cinemaspringboot.service;


import com.school21.cinemaspringboot.model.Message;

import java.util.List;

public interface MessageService {

    List<Message> getLastTwelveMessagesFromFilmId(Long filmId);
    void save(Message message);
}
