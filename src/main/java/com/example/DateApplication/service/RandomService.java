package com.example.DateApplication.service;

import com.example.DateApplication.dto.DateIdea;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class RandomService {
    public DateIdea getRandomDateIdea(List<DateIdea> dateIdeas) throws NullPointerException {
            Random random = new Random();
            int index = random.nextInt(dateIdeas.size());
            return dateIdeas.get(index);
    }
}
