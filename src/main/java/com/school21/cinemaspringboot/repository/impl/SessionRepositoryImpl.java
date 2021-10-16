package com.school21.cinemaspringboot.repository.impl;

import com.school21.cinemaspringboot.model.Session;
import com.school21.cinemaspringboot.repository.SessionRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SessionRepositoryImpl implements SessionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Session> getAll() {
        return entityManager.createQuery("FROM Session", Session.class).getResultList();
    }

    @Override
    @Transactional
    public Session get(Long id) {
        return entityManager.find(Session.class, id);
    }

    @Override
    @Transactional
    public void save(Session session) {
        entityManager.persist(session);
    }

    //функционал для ex01
    @Override
    public List<Session> searchByRequest(String request) {
        request = request + "%";
        return entityManager.createQuery("SELECT new Session(s.id, s.ticketCost, s.date, f) " +
                "FROM Session s JOIN Film f ON s.film.filmId = f.filmId " +
                "WHERE f.title LIKE :request", Session.class)
                .setParameter("request", request)
                .getResultList();
    }
}
