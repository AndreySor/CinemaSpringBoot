package com.school21.cinemaspringboot.controller;

import com.school21.cinemaspringboot.model.SecureToken;
import com.school21.cinemaspringboot.model.User;
import com.school21.cinemaspringboot.repository.UserRepository;
import com.school21.cinemaspringboot.service.Impl.EmailServiceImpl;
import com.school21.cinemaspringboot.service.Impl.SecureTokenServiceImpl;
import com.school21.cinemaspringboot.service.Impl.UserServiceImpl;
import com.school21.cinemaspringboot.service.SecureTokenService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UserRepository userRepository;
    private final UserServiceImpl userService;
    private final EmailServiceImpl emailService;
    private final SecureTokenService secureTokenService;

    public AuthController(UserRepository userRepository, UserServiceImpl userService, EmailServiceImpl emailService, SecureTokenService secureTokenService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.emailService = emailService;
        this.secureTokenService = secureTokenService;
    }

    @GetMapping(value = "/signIn")
    public String signIn(Model model) {

        User user = new User();
        model.addAttribute("user", user);
        return "signIn";
    }

    @GetMapping(value = "/profile")
    public String getProfile(Model model) {

        return "profile";
    }

    @GetMapping(value = "/signUp")
    public String signUp(Model model) {

        User user = new User();
        model.addAttribute("user", user);
        return "signUp";
    }

    @PostMapping(value = "/signUp")
    public String addUser(Model model, @ModelAttribute("user") User user) {

        try {
            user = userService.saveUser(user);
            SecureToken secureToken = secureTokenService.createSecureToken();
            secureToken.setUser(user);
            emailService.sendConfirmEmail(user, secureToken.getToken());
        } catch (RuntimeException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "signUp";
        }

        return "signIn";
    }

    @GetMapping(value = "/confirm/{token}")
    public String confirm(@PathVariable("token") String token) {

        if (token.isEmpty()) {

            return "signUp";
        }
         SecureToken secureToken = secureTokenService.findByToken(token);

        if (secureToken == null) {

            return "signUp";
        }

        User user = secureToken.getUser();
        user.setConfirmed(true);
        userService.saveUser(user);
        return "signIn";
    }
}
