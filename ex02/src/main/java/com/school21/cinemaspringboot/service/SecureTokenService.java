package com.school21.cinemaspringboot.service;

import com.school21.cinemaspringboot.model.SecureToken;

public interface SecureTokenService {

    SecureToken createSecureToken();
    void saveSecureToken(SecureToken token);
    SecureToken findByToken(String token);
    void removeToken(SecureToken token);
    void removeTokenByToken(String token);
}
