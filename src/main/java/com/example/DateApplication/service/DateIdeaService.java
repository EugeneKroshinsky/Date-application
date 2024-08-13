package com.example.DateApplication.service;

import com.example.DateApplication.dto.DateIdea;
import com.example.DateApplication.dto.FindRequest;
import com.example.DateApplication.dto.entities.DateIdeaEntity;
import com.example.DateApplication.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DateIdeaService {
    private final static Logger logger = LoggerFactory.getLogger(DateIdeaService.class);
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
        try {
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
            logger.info("DateIdea with id={} was successfully saved", dateIdea.getId());
        } catch (Exception e) {
            logger.error("An error occurred while saving DateIdea", e);
        }
    }

    public DateIdea findById(int id) {
        return dateIdeaRepository.findById(id).map(DateIdea::new)
                .orElseThrow(() -> {
                    logger.error("An error occurred while getting DateIdea with id = {}", id);
                    return new NoEntityException(id);
                });
    }

    public void deleteIdea(int id) {
        try{
            dateIdeaRepository.deleteById(id);
            logger.info("DateIdea with id={} was successfully deleted", id);
        } catch(Exception e) {
            logger.error("An error occurred while deleting DateIdea with id = {}", id);
        }

    }
}
