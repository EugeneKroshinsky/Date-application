package com.example.DateApplication.service;

import com.example.DateApplication.dto.DateIdea;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RandomService {
    public DateIdea getRandomDateIdea(List<DateIdea> dateIdeas) {
        return new DateIdea();
    }
}
