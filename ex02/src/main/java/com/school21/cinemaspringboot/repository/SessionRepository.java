package com.school21.cinemaspringboot.repository;


import com.school21.cinemaspringboot.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long>, SessionRepositoryPart {

}
