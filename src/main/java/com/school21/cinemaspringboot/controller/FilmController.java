package com.school21.cinemaspringboot.controller;

import com.school21.cinemaspringboot.model.Film;
import com.school21.cinemaspringboot.model.SaveFilm;
import com.school21.cinemaspringboot.repository.FilmRepository;
import com.school21.cinemaspringboot.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.util.List;

@Controller
public class FilmController {

    private final FilmRepository filmRepository;

    private final FilmService filmService;

    @Autowired
    public FilmController(FilmRepository filmRepository,
                          FilmService filmService) {
        this.filmRepository = filmRepository;
        this.filmService = filmService;
    }

    @RequestMapping(value = "/admin/panel/films", method = RequestMethod.GET)
    public String showAllFilm(Model model) {
        List<Film> films = filmRepository.findAll();
        model.addAttribute("films", films);
        return "films";
    }

    @RequestMapping(value = "/admin/panel/addNewFilm", method = RequestMethod.GET)
    public String addNewFilm(Model model) {
        SaveFilm film = new SaveFilm();
        model.addAttribute("film", film);

        return "addFilm";
    }

    @RequestMapping(value = "/admin/panel/saveNewFilm", method = RequestMethod.POST)
    public String saveNewFilm(Model model, @ModelAttribute("film") SaveFilm film) {
        try {
            filmService.saveFilm(film);
        } catch (RuntimeException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "addFilm";
        }
        return "redirect:/admin/panel/films";
    }

    @RequestMapping(value = "/admin/panel/addPoster", method = RequestMethod.POST)
    public String addPoster(Model model, @ModelAttribute("film") Film film) {
        SaveFilm saveFilm = new SaveFilm(film.getTitle());
        model.addAttribute("saveFilm", saveFilm);
        return "addPoster";
    }

    @RequestMapping(value = "/admin/panel/savePoster", method = RequestMethod.POST)
    public String savePoster(Model model, @ModelAttribute("saveFilm") SaveFilm film) {
        try {
            filmService.updateFilm(film);
        } catch (RuntimeException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "addFilm";
        }
        return "redirect:/admin/panel/films";
    }
}
