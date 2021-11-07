package com.school21.cinemaspringboot.service.Impl;

import com.school21.cinemaspringboot.model.Session;
import com.school21.cinemaspringboot.repository.SessionRepository;
import com.school21.cinemaspringboot.service.SessionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;

    public SessionServiceImpl(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public void save(Session session) {
        sessionRepository.save(session);
    }

    @Override
    public List<Session> getAll() {
        return sessionRepository.findAll();
    }

    @Override
    public Session get(Long id) {
        return sessionRepository.findById(id).get();
    }

    @Override
    public List<Session> searchByRequest(String request) {
        return sessionRepository.searchByRequest(request);
    }
}
