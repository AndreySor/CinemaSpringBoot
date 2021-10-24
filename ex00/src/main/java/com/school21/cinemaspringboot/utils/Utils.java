package com.school21.cinemaspringboot.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class Utils {

    public String selectRedirectUrl(Authentication authentication) {
        Map<String, String> redirectMap = new HashMap<>();
        redirectMap.put("ROLE_USER", "/profile");
        redirectMap.put("ROLE_ADMIN", "/admin/panel/halls");

        final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (final GrantedAuthority grantedAuthority : authorities) {

            String authorityName = grantedAuthority.getAuthority();
            if(redirectMap.containsKey(authorityName)) {
                return redirectMap.get(authorityName);
            }
        }

        throw new IllegalStateException();
    }
}
