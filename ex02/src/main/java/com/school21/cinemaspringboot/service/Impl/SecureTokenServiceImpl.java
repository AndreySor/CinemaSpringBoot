package com.school21.cinemaspringboot.service.Impl;

import com.school21.cinemaspringboot.model.SecureToken;
import com.school21.cinemaspringboot.repository.SecureTokenRepository;
import com.school21.cinemaspringboot.service.SecureTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SecureTokenServiceImpl implements SecureTokenService {

    @Autowired
    SecureTokenRepository secureTokenRepository;

    @Override
    public SecureToken createSecureToken() {
        SecureToken token = new SecureToken();
        token.setToken(UUID.randomUUID().toString());
        this.saveSecureToken(token);
        return token;
    }

    @Override
    public void saveSecureToken(SecureToken token) {
        secureTokenRepository.save(token);

    }

    @Override
    public SecureToken findByToken(String token) {
        return secureTokenRepository.findByToken(token);
    }

    @Override
    public void removeToken(SecureToken token) {
        secureTokenRepository.delete(token);
    }

    @Override
    public void removeTokenByToken(String token) {
        secureTokenRepository.deleteByToken(token);
    }
}
