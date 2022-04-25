package com.example.demo.service;

import com.example.demo.model.Author;


import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Optional<Author> findById(Long id);
    Optional<Author> create(String name, String surname, Long country);
    List<Author> listAll();
}
