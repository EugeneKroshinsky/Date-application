package com.example.DateApplication.repositories;

import com.example.DateApplication.dto.entities.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegionRepository extends JpaRepository<RegionEntity, Long> {
    List<RegionEntity> findAll();
    List<RegionEntity> findByCountryId(int countryId);
}
