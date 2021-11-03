package com.school21.cinemaspringboot.controller;

import com.school21.cinemaspringboot.model.Authentication;
import com.school21.cinemaspringboot.model.Film;
import com.school21.cinemaspringboot.model.Message;
import com.school21.cinemaspringboot.repository.FilmRepository;
import com.school21.cinemaspringboot.service.AuthenticationService;
import com.school21.cinemaspringboot.service.ChatService;
import com.school21.cinemaspringboot.service.MessageService;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class ChatController {

    private final AuthenticationService authenticationService;
    private final MessageService messageService;
    private final FilmRepository filmRepository;
    private final ChatService chatService;

    public ChatController(AuthenticationService authenticationService,
                          MessageService messageService,
                          FilmRepository filmRepository,
                          ChatService chatService) {
        this.authenticationService = authenticationService;
        this.messageService = messageService;
        this.filmRepository = filmRepository;
        this.chatService = chatService;
    }

    @GetMapping(value = "/films/{film-id}/chat")
    public String startChat(@PathVariable("film-id") Long filmId, Model model) {
        Film film = filmRepository.findByFilmId(filmId);
        List<Message> lastMessages = messageService.getLastTwelveMessagesFromFilmId(filmId);
        model.addAttribute("film", film);
        model.addAttribute("history", lastMessages);
        return "chat";
    }

    @GetMapping(value = "/userInfo/auth", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.ALL_VALUE)
    public @ResponseBody List<Authentication> getAuthList(@ModelAttribute("id") Long id) {
        List<Authentication> rs = authenticationService.getAllByUserId(id);
        if (rs.isEmpty()) {
            return null;
        }
        return rs;
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public Message sendMessage(@Payload Message message) {
        if (message.getType().equals(Message.MessageType.CHAT)) {
            messageService.save(message);
        }
        return message;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public Message addUser(@Payload Message message, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", message.getUser().getLogin());
        String clientIp = (String) headerAccessor.getSessionAttributes().get("ip");
        message.getUser().setId(authenticationService.authUser(message.getUser(), clientIp));
        return message;
    }


    @PostMapping(value = "/images", consumes = "multipart/form-data")
    public String uploadAvatar(@ModelAttribute("avatar") MultipartFile avatar,
                               @ModelAttribute("filmId") Long filmId,
                               @ModelAttribute("userId") Long userId) throws IOException {
        chatService.saveAvatar(avatar, userId);
        return "redirect:/films/" + filmId + "/chat";
    }

    @GetMapping(value = "/chat/usersAvatars/list", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.ALL_VALUE)
    public @ResponseBody List<String> getListOfAvatars(@ModelAttribute("id") Long id) {
        return chatService.getListOfAvatarByUserId(id);
    }

    @GetMapping(value = "/chat/avatar/{userId}/{fileName}")
    public String getListOfAvatars(@PathVariable("fileName") String fileName, @PathVariable("userId") Long userId,  Model model) {
        String avatar = chatService.getAvatar(fileName, userId);
        model.addAttribute("avatar", avatar);
        return "images";
    }
}
