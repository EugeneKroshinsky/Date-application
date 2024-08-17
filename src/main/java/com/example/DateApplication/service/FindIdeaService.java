package com.example.DateApplication.service;

import com.example.DateApplication.dto.DateIdea;
import com.example.DateApplication.dto.FindRequest;
import com.example.DateApplication.repositories.DateIdeaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindIdeaService {
    private final DAOService daoService;

    @Autowired
    public FindIdeaService(DAOService daoService) {
        this.daoService = daoService;
    }

    public List<DateIdea> getDateIdeas(FindRequest findRequest) {
        return daoService.findDateIdeas(findRequest);
    }

}
