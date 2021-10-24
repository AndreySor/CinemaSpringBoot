package com.school21.cinemaspringboot.repository;

import com.school21.cinemaspringboot.model.SecureToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecureTokenRepository extends JpaRepository<SecureToken, Long> {
    SecureToken findByToken(String token);
    void deleteByToken(String token);
}
