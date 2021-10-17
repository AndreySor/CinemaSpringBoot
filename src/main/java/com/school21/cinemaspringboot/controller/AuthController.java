package com.school21.cinemaspringboot.controller;

import com.school21.cinemaspringboot.model.User;
import com.school21.cinemaspringboot.repository.UserRepository;
import com.school21.cinemaspringboot.service.Impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class AuthController {

    private final UserRepository userRepository;
    private final UserServiceImpl userService;

    public AuthController(UserRepository userRepository, UserServiceImpl userService) {
        this.userRepository = userRepository;
        this.userService = userService;
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
//        String tr = principal.getName();
//        User user = (User) model.getAttribute("user");
        if (user == null || user.getLogin() == null || user.getPassword() == null) {
            model.addAttribute("errorMessage", "Введите обязательные данные");
            return "signUp";
        }
        User user1 = userRepository.findByLogin(user.getLogin());
        if (user1 != null) {
            model.addAttribute("errorMessage", "Пользователь уже существует");
            return "signUp";
        }

        userService.saveUser(user);

        return "signIn";
    }
}
