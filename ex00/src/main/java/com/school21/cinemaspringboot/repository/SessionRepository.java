package com.school21.cinemaspringboot.repository;

import com.school21.cinemaspringboot.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long>, SessionRepositoryPart {

}
