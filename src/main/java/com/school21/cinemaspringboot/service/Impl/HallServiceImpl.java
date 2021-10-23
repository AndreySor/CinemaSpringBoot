package com.school21.cinemaspringboot.service.Impl;

import com.school21.cinemaspringboot.model.Hall;
import com.school21.cinemaspringboot.repository.HallRepository;
import com.school21.cinemaspringboot.service.HallService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HallServiceImpl implements HallService {

    private final HallRepository hallRepository;

    public HallServiceImpl(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

    @Override
    public List<Hall> getAll() {
        return hallRepository.findAll();
    }
}
