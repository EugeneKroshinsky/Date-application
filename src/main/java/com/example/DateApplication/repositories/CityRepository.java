package com.example.DateApplication.repositories;

import com.example.DateApplication.dto.entities.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<CityEntity, Long> {
    List<CityEntity> findAll();
    List<CityEntity> findByRegionId(int regionId);
}
