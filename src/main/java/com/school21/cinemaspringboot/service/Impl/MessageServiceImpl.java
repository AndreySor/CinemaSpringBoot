package com.school21.cinemaspringboot.service.Impl;


import com.school21.cinemaspringboot.model.Message;
import com.school21.cinemaspringboot.repository.MessageRepository;
import com.school21.cinemaspringboot.service.MessageService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class MessageServiceImpl implements MessageService {

    private MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public List<Message> getLastTwelveMessagesFromFilmId(Long filmId) {
        return messageRepository.getLastTwelveMessagesFromFilmId(filmId);
    }

    @Override
    public void save(Message message) {
        message.setDate(new Date());
        messageRepository.save(message);
    }
}
