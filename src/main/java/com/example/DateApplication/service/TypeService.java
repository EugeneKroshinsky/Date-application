package com.example.DateApplication.service;

import com.example.DateApplication.dto.entities.TypeEntity;
import com.example.DateApplication.repositories.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeService {
    private final TypeRepository typeRepository;

    @Autowired
    public TypeService(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    public List<TypeEntity> findAll() {
        return typeRepository.findAll();
    }
}
