package com.school21.cinemaspringboot.controller;

import com.school21.cinemaspringboot.model.Film;
import com.school21.cinemaspringboot.model.Hall;
import com.school21.cinemaspringboot.model.Session;
import com.school21.cinemaspringboot.service.FilmService;
import com.school21.cinemaspringboot.service.HallService;
import com.school21.cinemaspringboot.service.SessionService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
@RequestMapping(value = "/admin/panel")
public class SessionAdminController {

    private final SessionService sessionService;
    private final FilmService filmService;
    private final HallService hallService;
    private final SimpleDateFormat formatter;

    public SessionAdminController(SessionService sessionService, FilmService filmService, HallService hallService) {
        this.sessionService = sessionService;
        this.filmService = filmService;
        this.hallService = hallService;
        this.formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
    }

    @GetMapping(value = "/sessions", consumes = MediaType.ALL_VALUE)
    public String getSessionList(Model model) {
        model.addAttribute("sessions", sessionService.getAll());
        model.addAttribute("halls", hallService.getAll());
        model.addAttribute("films", filmService.getAll());

        return "sessions";
    }

    @PostMapping(value = "/addSession", consumes = MediaType.ALL_VALUE)
    public String addSession(@ModelAttribute("selectedFilm")Long filmId,
                             @ModelAttribute("selectedHall")Long hallId,
                             @ModelAttribute("ticketCost")Integer ticketCost,
                             @ModelAttribute("sessionDate")String date) throws ParseException {
        Session session = new Session();
        session.setHall(new Hall().withId(hallId));
        session.setFilm(new Film().withId(filmId));
        session.setTicketCost(ticketCost);
        session.setDate(formatter.parse(date));
        sessionService.save(session);

        return "redirect:/admin/panel/sessions";
    }
}
