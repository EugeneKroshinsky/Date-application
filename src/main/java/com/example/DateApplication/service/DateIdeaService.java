package com.example.DateApplication.service;

import com.example.DateApplication.dto.DateIdea;
import com.example.DateApplication.dto.FindRequest;
import com.example.DateApplication.dto.entities.DateIdeaEntity;
import com.example.DateApplication.repositories.DateIdeaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DateIdeaService {
    @Autowired
    private DateIdeaRepository dateIdeaRepository;


    public List<DateIdea> getDateIdeas(FindRequest findRequest) {
        //TODO Реализовать логику поиску по полученным параметрам
        return dateIdeaRepository.findAll().stream()
                .map(DateIdea::new)
                .toList();
    }
}
