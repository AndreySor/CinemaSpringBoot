package com.school21.cinemaspringboot.repository;


import com.school21.cinemaspringboot.model.Authentication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthenticationRepository extends JpaRepository<Authentication, Long> {

    Authentication get(Long id);

    List<Authentication> findAllByUserId(Long id);
}
