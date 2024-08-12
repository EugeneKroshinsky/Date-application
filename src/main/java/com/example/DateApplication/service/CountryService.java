package com.example.DateApplication.service;

import com.example.DateApplication.dto.entities.CountryEntity;
import com.example.DateApplication.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {
    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<CountryEntity> findAll() {
        return countryRepository.findAll();
    }
}
