package com.school21.cinemaspringboot.security;

import com.school21.cinemaspringboot.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

@Component
public class RedirectUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private Utils utils;
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    public void setUtils(Utils utils) {
        this.utils = utils;
    }

    public RedirectUrlAuthenticationSuccessHandler() {
        super();
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        final String targetUrl = utils.selectRedirectUrl(authentication);
        Cookie userCookie = new Cookie("login", authentication.getName());
        httpServletResponse.addCookie(userCookie);
        redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, targetUrl);
        clearAuthenticationAttributes(httpServletRequest);
    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
