package com.school21.cinemaspringboot.repository;


import com.school21.cinemaspringboot.model.Authentication;

import java.util.List;

public interface AuthenticationRepository {

    List<Authentication> getAll();
    Authentication get(Long id);
    Authentication save(Authentication authentication);
    Long getCountRows();
    List<Authentication> getAllByUserId(Long id);
}
