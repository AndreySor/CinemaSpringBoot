package com.school21.cinemaspringboot.controller;

import com.school21.cinemaspringboot.model.User;
import com.school21.cinemaspringboot.repository.UserRepository;
import com.school21.cinemaspringboot.service.Impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;

@Controller
public class AuthController {

    private final UserRepository userRepository;
    private final UserServiceImpl userService;

    public AuthController(UserRepository userRepository, UserServiceImpl userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/")
    public String root() {
        return "redirect:signIn";
    }

    @GetMapping("/signIn")
    public String signIn(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "signIn";
    }

    @GetMapping(value = "/profile")
    public String getProfile(Model model, HttpServletRequest request) {
        String login = Arrays.stream(request.getCookies())
                .filter(c -> c.getName().equals("login"))
                .findFirst()
                .get().getValue();
        User user = userRepository.findByLogin(login);
        if (user.getAvatar() != null) {
            user.setAvatar(userService.getAvatar(user));
        }
        model.addAttribute("userInfo", user);
        return "profile";
    }

    @PostMapping(value = "/avatarUpload", consumes = "multipart/form-data")
    public String uploadAvatar(@ModelAttribute("avatar")MultipartFile avatar,
                               @ModelAttribute("userId")Long userId) throws IOException {
        userService.uploadAvatar(avatar, userId);
        return "redirect:profile";
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
            userService.saveUser(user);
        } catch (RuntimeException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "signUp";
        }

        return "signIn";
    }
}
