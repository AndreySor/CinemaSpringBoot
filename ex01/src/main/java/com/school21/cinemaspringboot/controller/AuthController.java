package com.school21.cinemaspringboot.controller;

import com.school21.cinemaspringboot.model.User;
import com.school21.cinemaspringboot.repository.UserRepository;
import com.school21.cinemaspringboot.service.Impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AuthController {

    private final UserRepository userRepository;
    private final UserServiceImpl userService;

    public AuthController(UserRepository userRepository, UserServiceImpl userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/signIn")
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
    public String addUser(Model model, @Valid User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "signUp";
        }

        try {
            userService.saveUser(user);
        } catch (RuntimeException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "signUp";
        }

        return "signIn";
    }
}
