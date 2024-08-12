package com.example.DateApplication.service;

import com.example.DateApplication.dto.DateIdea;
import com.example.DateApplication.dto.FindRequest;
import com.example.DateApplication.dto.entities.CityEntity;
import com.example.DateApplication.dto.entities.DateIdeaEntity;
import com.example.DateApplication.repositories.*;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DateIdeaService {
    private final TypeRepository typeRepository;
    private final DateIdeaRepository dateIdeaRepository;
    private final CountryRepository countryRepository;
    private final RegionRepository regionRepository;
    private final CityRepository cityRepository;

    @Autowired
    public DateIdeaService(TypeRepository typeRepository,
                           DateIdeaRepository dateIdeaRepository,
                           CountryRepository countryRepository,
                           RegionRepository regionRepository,
                           CityRepository cityRepository) {
        this.typeRepository = typeRepository;
        this.dateIdeaRepository = dateIdeaRepository;
        this.countryRepository = countryRepository;
        this.regionRepository = regionRepository;
        this.cityRepository = cityRepository;
    }

    public List<DateIdea> getDateIdeas(FindRequest findRequest) {
        //TODO Реализовать логику поиска по полученным параметрам
        return dateIdeaRepository.findAll().stream()
                .map(DateIdea::new)
                .toList();
    }

    public void save(DateIdea dateIdea) {
        DateIdeaEntity dateIdeaEntity = new DateIdeaEntity();
        dateIdeaEntity.setName(dateIdea.getName());
        dateIdeaEntity.setDate(new Date());
        dateIdeaEntity.setType(typeRepository.findById(Integer.parseInt(dateIdea.getType())).orElse(null));
        dateIdeaEntity.setCountry(countryRepository.findById(Integer.parseInt(dateIdea.getCountry())).orElse(null));
        dateIdeaEntity.setRegion(regionRepository.findById(Integer.parseInt(dateIdea.getRegion())).orElse(null));
        dateIdeaEntity.setCity(cityRepository.findById(Integer.parseInt(dateIdea.getCity())).orElse(null));
        dateIdeaEntity.setAddress(dateIdea.getAddress());
        dateIdeaEntity.setMaxPrice(dateIdea.getMaxPrice());
        dateIdeaEntity.setMinPrice(dateIdea.getMinPrice());
        dateIdeaEntity.setDescription(dateIdea.getDescription());
        dateIdeaRepository.save(dateIdeaEntity);
    }

    public DateIdea findById(int id) {
        Optional<DateIdeaEntity> dateIdeaEntity = dateIdeaRepository.findById(id);
        //Обработка исключений
        return dateIdeaEntity.map(DateIdea::new).orElse(null);
    }

    public void deleteIdea(int id) {
        dateIdeaRepository.deleteById(id);
    }
}
