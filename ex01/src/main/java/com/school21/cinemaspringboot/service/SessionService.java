package com.school21.cinemaspringboot.service;


import com.school21.cinemaspringboot.model.Session;

import java.util.List;

public interface SessionService {

    void save(Session session);
    List<Session> getAll();
    Session get(Long id);

    //функционал для ex01
    List<Session> searchByRequest(String request);
}
