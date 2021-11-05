package com.school21.cinemaspringboot.repository;

import com.school21.cinemaspringboot.model.Session;

import java.util.List;

public interface SessionRepositoryPart {

    List<Session> searchByRequest(String request);
}
