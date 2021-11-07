package com.school21.cinemaspringboot.service;

import com.school21.cinemaspringboot.model.User;

import javax.mail.MessagingException;

public interface EmailService {
    void sendConfirmEmail(User user, String token) throws MessagingException;
}
