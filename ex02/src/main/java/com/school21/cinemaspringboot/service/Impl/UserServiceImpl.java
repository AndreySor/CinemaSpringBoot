package com.school21.cinemaspringboot.service.Impl;

import com.school21.cinemaspringboot.exception.NotAllDataException;
import com.school21.cinemaspringboot.exception.ObjectAlreadyExistsException;
import com.school21.cinemaspringboot.model.Role;
import com.school21.cinemaspringboot.model.User;
import com.school21.cinemaspringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " was not found");
        }
        boolean enabled = !user.getConfirmed();
        Set<GrantedAuthority> roles = new HashSet();
        roles.add(new SimpleGrantedAuthority(user.getRole().name()));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getLogin())
                .password(user.getPassword())
                .disabled(enabled)
                .authorities(roles)
                .build();
    }

    public User saveUser(User user) {
        if (user == null || user.getLogin() == null
                || user.getPassword() == null || user.getFirstName() == null
                || user.getLastName() == null || user.getEmail() == null || user.getPhone() == null) {
            throw new NotAllDataException("Please enter all data");
        }
        if (userRepository.findByLogin(user.getLogin()) != null) {
            throw new ObjectAlreadyExistsException("A user with this login already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_USER);
        return userRepository.save(user);
    }
}
