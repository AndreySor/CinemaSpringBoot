package com.school21.cinemaspringboot.controller;


import com.school21.cinemaspringboot.mapper.SessionsResponseMapper;
import com.school21.cinemaspringboot.model.Session;
import com.school21.cinemaspringboot.model.response.BaseResponse;
import com.school21.cinemaspringboot.service.SessionService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//функционал для ex01

@Controller
@RequestMapping(value = "/sessions")
public class SessionController {

    private final SessionService sessionService;
    private final SessionsResponseMapper mapper;

    public SessionController(SessionService sessionService, SessionsResponseMapper mapper) {
        this.sessionService = sessionService;
        this.mapper = mapper;
    }

    @GetMapping(value = "/")
    public String getSearchField() {
        return "sessionsSearching";
    }

    @GetMapping(value = "/search", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    BaseResponse searching(@ModelAttribute("filmName") String request) {
        if (request.isEmpty()) {
            return null;
        }
        List<Session> serviceResponse = sessionService.searchByRequest(request);
        return new BaseResponse(mapper.mapToResponse(serviceResponse));
    }

    @GetMapping(value = "/{session-id}")
    public String getFilmInfoBySessionId(@PathVariable("session-id") Long sessionId, Model model) {
        Session session = sessionService.get(sessionId);
        mapper.encodePoster(session.getFilm());
        model.addAttribute("info", session);
        return "filmSessionInfo";
    }
}
