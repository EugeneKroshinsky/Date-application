package com.example.DateApplication;

import com.example.DateApplication.dto.RegionDTO;
import com.example.DateApplication.dto.CityDTO;
import com.example.DateApplication.service.CityService;
import com.example.DateApplication.service.RegionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LocationController {

    private final RegionService regionService;
    private final CityService cityService;

    public LocationController(RegionService regionService, CityService cityService) {
        this.regionService = regionService;
        this.cityService = cityService;
    }

    @GetMapping("/regions")
    public List<RegionDTO> getRegionsByCountry(@RequestParam int countryId) {
        return regionService.getRegionsByCountryId(countryId);
    }

    @GetMapping("/cities")
    public List<CityDTO> getCitiesByRegion(@RequestParam int regionId) {
        return cityService.getCitiesByRegionId(regionId);
    }
}
