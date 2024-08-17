package com.example.DateApplication.service;

import com.example.DateApplication.dto.DateIdea;
import com.example.DateApplication.dto.FindRequest;
import com.example.DateApplication.repositories.DateIdeaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FindIdeaService {
    private final DateIdeaRepository dateIdeaRepository;

    @Autowired
    public FindIdeaService(DateIdeaRepository dateIdeaRepository) {
        this.dateIdeaRepository = dateIdeaRepository;
    }

    public List<DateIdea> getDateIdeas(FindRequest findRequest) {
        return dateIdeaRepository.findAll().stream()
                .map(DateIdea::new)
                .toList();
    }

}
