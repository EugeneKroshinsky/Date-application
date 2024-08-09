package com.example.DateApplication.service;

import com.example.DateApplication.dto.RegionDTO;
import com.example.DateApplication.dto.entities.RegionEntity;
import com.example.DateApplication.repositories.RegionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegionService {

    private final RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public List<RegionDTO> getRegionsByCountryId(int countryId) {
        List<RegionEntity> regions = regionRepository.findByCountryId(countryId);
        return regions.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private RegionDTO convertToDTO(RegionEntity region) {
        return new RegionDTO(region.getId(), region.getName());
    }
}