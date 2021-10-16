package com.school21.cinemaspringboot.repository;


import com.school21.cinemaspringboot.model.Session;

import java.util.List;

public interface SessionRepository extends BaseCRUDRepository<Session> {

    //функционал для ex01
    List<Session> searchByRequest(String request);
}
