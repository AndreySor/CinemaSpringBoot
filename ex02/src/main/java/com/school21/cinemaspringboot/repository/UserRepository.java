package com.school21.cinemaspringboot.repository;

import com.school21.cinemaspringboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(String login);
}
