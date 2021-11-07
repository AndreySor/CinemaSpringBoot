package com.school21.cinemaspringboot.repository;

import com.school21.cinemaspringboot.model.Hall;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HallRepository extends JpaRepository<Hall, Long> {

    Hall findBySerialNumber(Integer serialNumber);
}
