package com.school21.cinemaspringboot.service.Impl;

import com.school21.cinemaspringboot.model.Authentication;
import com.school21.cinemaspringboot.model.SecureToken;
import com.school21.cinemaspringboot.model.User;
import com.school21.cinemaspringboot.repository.AuthenticationRepository;
import com.school21.cinemaspringboot.repository.UserRepository;
import com.school21.cinemaspringboot.service.AuthenticationService;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationRepository authenticationRepository;
    private final UserRepository userRepository;

    public AuthenticationServiceImpl(AuthenticationRepository authenticationRepository,
                                     UserRepository userRepository) {
        this.authenticationRepository = authenticationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Long authUser(User user, String ip) {
        User checkUser = userRepository.findByLogin(user.getLogin());
        if (checkUser == null) {
            user.setId(userRepository.save(user).getId());
        } else {
            user = checkUser;
        }
        authenticationRepository.save(new Authentication()
                .withIpAddress(ip)
                .withDate(new Date())
                .withUser(user));
        return user.getId();
    }

    @Override
    public List<Authentication> getAllByUserId(Long id) {
        return authenticationRepository.findAllByUserId(id);
    }
}
