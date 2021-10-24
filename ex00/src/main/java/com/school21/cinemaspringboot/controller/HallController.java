package com.school21.cinemaspringboot.controller;


import com.school21.cinemaspringboot.model.Hall;
import com.school21.cinemaspringboot.repository.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class HallController {

    private final HallRepository hallRepository;

    @Autowired
    public HallController(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

    @RequestMapping(value = "/admin/panel/halls", method = RequestMethod.GET)
    public String showAllHals(Model model) {
        List<Hall> halls = hallRepository.findAll();
        model.addAttribute("halls", halls);

        return "halls";
    }

    @RequestMapping(value = "/admin/panel/addNewHall", method = RequestMethod.GET)
    public String addNewHall(Model model) {
        Hall hall = new Hall();
        model.addAttribute("hall", hall);

        return "addHall";
    }
    @RequestMapping(value = "/admin/panel/saveNewHall", method = RequestMethod.POST)
    public String saveNewHall(Model model, @ModelAttribute("hall") Hall hall) {
        if (hall == null || hall.getSerialNumber() == null || hall.getSeatsNumber() == null) {
            model.addAttribute("errorMessage", "Please enter all data");
            return "addHall";
        } else if (hallRepository.findBySerialNumber(hall.getSerialNumber()) != null) {
            model.addAttribute("errorMessage", "A hall with this number already exists");
            return "addHall";
        } else {
            hallRepository.save(hall);
        }
        return "redirect:/admin/panel/halls";
    }
}
