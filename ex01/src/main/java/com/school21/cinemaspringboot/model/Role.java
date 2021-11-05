package com.school21.cinemaspringboot.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
//public enum Role {
    ROLE_ADMIN,
    ROLE_USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
