package com.school21.cinemaspringboot.repository;

import java.util.List;

public interface BaseCRUDRepository<T> {
    List<T> getAll();
    T get(Long id);
    void save(T entity);
}
