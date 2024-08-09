package com.example.DateApplication.dto;


import com.example.DateApplication.dto.entities.CityEntity;
import com.example.DateApplication.dto.entities.CountryEntity;
import com.example.DateApplication.dto.entities.RegionEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
public class DateIdea {
    private int id;
    private Date date;

    @NotEmpty(message = "Name can't be empty")
    @Size(min=2, max=60, message = "Name should be between 2 and 60 characters")
    private String name;

    @NotEmpty
    private String country;
    @NotEmpty
    private String region;
    @NotEmpty
    private String city;

    @Size(min=2, max=60, message = "address should be between 2 and 60 characters")
    private String address;

    @NotEmpty(message = "type can't be empty")
    private String type;

    @Min(value=0, message = "minPrice should be greater than 0")
    private int minPrice;

    @Min(value=0, message = "minPrice should be greater than 0")
    private int maxPrice;

    @NotEmpty(message = "description can't be empty")
    private String description;

    public DateIdea() {
        date = new Date();
    }
}
