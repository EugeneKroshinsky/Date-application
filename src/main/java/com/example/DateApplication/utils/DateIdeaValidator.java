package com.example.DateApplication.utils;

import com.example.DateApplication.dto.DateIdea;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class DateIdeaValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return DateIdea.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        DateIdea dateIdeaEntity = (DateIdea) target;
        if (dateIdeaEntity.getMinPrice() > dateIdeaEntity.getMaxPrice()) {
            errors.rejectValue("maxPrice", "","maxValue should be greater than minValue");
        }
    }
}
