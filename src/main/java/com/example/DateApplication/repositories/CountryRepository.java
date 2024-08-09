package com.example.DateApplication.repositories;

import com.example.DateApplication.dto.entities.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepository extends JpaRepository<CountryEntity, Long> {
    List<CountryEntity> findAll();
}
