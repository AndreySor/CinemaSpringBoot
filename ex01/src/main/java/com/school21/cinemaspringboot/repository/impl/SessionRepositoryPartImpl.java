package com.school21.cinemaspringboot.repository.impl;

import com.school21.cinemaspringboot.model.Session;
import com.school21.cinemaspringboot.repository.SessionRepository;
import com.school21.cinemaspringboot.repository.SessionRepositoryPart;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SessionRepositoryPartImpl implements SessionRepositoryPart {

    @PersistenceContext
    private EntityManager entityManager;

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
