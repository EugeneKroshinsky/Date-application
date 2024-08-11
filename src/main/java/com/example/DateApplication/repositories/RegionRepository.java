package com.example.DateApplication.repositories;

import com.example.DateApplication.dto.entities.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionRepository extends JpaRepository<RegionEntity, Integer> {
    @Query("SELECT r FROM RegionEntity r WHERE r.country.id = :countryId")
    List<RegionEntity> findByCountryId(@Param("countryId") int countryId);
}
