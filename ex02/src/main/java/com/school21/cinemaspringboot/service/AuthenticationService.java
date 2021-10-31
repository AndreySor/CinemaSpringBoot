package com.school21.cinemaspringboot.service;

import com.school21.cinemaspringboot.model.Authentication;
import com.school21.cinemaspringboot.model.SecureToken;
import com.school21.cinemaspringboot.model.User;

import java.util.List;

public interface AuthenticationService {

    Long authUser(User user, String ip);
    List<Authentication> getAllByUserId(Long id);
}
