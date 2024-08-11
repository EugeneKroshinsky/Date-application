package com.example.DateApplication.repositories;

import com.example.DateApplication.dto.entities.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Integer> {
    @Query("SELECT r FROM CityEntity r WHERE r.region.id = :regionId")
    List<CityEntity> findByRegionId(@Param("regionId") int regionId);
}
