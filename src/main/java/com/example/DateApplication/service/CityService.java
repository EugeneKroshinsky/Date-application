package com.example.DateApplication.service;

import com.example.DateApplication.dto.CityDTO;
import com.example.DateApplication.dto.entities.CityEntity;
import com.example.DateApplication.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<CityDTO> getCitiesByRegionId(int regionId) {
        List<CityEntity> cities = cityRepository.findByRegionId(regionId);
        return cities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private CityDTO convertToDTO(CityEntity city) {
        return new CityDTO(city.getId(), city.getName());
    }
}