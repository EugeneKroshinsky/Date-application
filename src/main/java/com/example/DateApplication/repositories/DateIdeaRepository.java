package com.example.DateApplication.repositories;

import com.example.DateApplication.dto.entities.DateIdeaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DateIdeaRepository extends JpaRepository<DateIdeaEntity, Long> {
    List<DateIdeaEntity> findAll();
    List<DateIdeaEntity> findById(int id);
}
