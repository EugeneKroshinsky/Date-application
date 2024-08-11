package com.example.DateApplication.repositories;

import com.example.DateApplication.dto.entities.DateIdeaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DateIdeaRepository extends JpaRepository<DateIdeaEntity, Integer> {
}
