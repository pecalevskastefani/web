package com.example.demo.service.impl;

import com.example.demo.model.Author;
import com.example.demo.model.Country;
import com.example.demo.model.exceptions.CountryNotFoundException;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.CountryRepository;
import com.example.demo.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public Optional<Author> create(String name, String surname, Long country) {
        Country country1 = this.countryRepository.findById(country)
                .orElseThrow(()->new CountryNotFoundException(country));
        Author author = new Author(name,surname,country1);
        this.authorRepository.save(author);
        return Optional.of(author);
    }

    @Override
    public List<Author> listAll() {
        return this.authorRepository.findAll();
    }
}
