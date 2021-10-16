package com.school21.cinemaspringboot.controller;

import com.school21.cinemaspringboot.model.User;
import com.school21.cinemaspringboot.repository.UserRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping(value = "/signIn")
    public String signIn(Model model) {
        User user = (User) model.getAttribute("signIn");
        if (user == null || user.getLogin() == null || user.getPassword() == null) {
            model.addAttribute("errorMessage", "Введите обязательные данные");
            return "signIn";
        }
        user = userRepository.findByLogin(user.getLogin());
        if (user == null) {
            model.addAttribute("errorMessage", "Пользователь не найден");
            return "signIn";
        } else if (user.getPassword() != user.getPassword()) {
            model.addAttribute("errorMessage", "Неправильный пароль");
            return "signIn";
        }
        return "profile";
    }
}
