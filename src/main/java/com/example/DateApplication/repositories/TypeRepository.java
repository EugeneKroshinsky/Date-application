package com.example.DateApplication.repositories;

import com.example.DateApplication.dto.entities.TypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TypeRepository extends JpaRepository<TypeEntity, Long> {
    List<TypeEntity> findAll();
}
