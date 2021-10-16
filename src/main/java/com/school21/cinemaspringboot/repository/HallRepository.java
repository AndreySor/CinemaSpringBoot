package com.school21.cinemaspringboot.repository;


import com.school21.cinemaspringboot.model.Hall;

public interface HallRepository extends BaseCRUDRepository<Hall> {

    Hall getFromSerialNumber(Integer serialNumber);
}
