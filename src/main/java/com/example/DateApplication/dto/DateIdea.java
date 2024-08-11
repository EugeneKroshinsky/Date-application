package com.example.DateApplication.dto;


import com.example.DateApplication.dto.entities.CityEntity;
import com.example.DateApplication.dto.entities.CountryEntity;
import com.example.DateApplication.dto.entities.DateIdeaEntity;
import com.example.DateApplication.dto.entities.RegionEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringJoiner;

@Setter
@Getter
@AllArgsConstructor
public class DateIdea {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");

    private int id;

    private String date;

    @NotEmpty(message = "Name can't be empty")
    @Size(min=2, max=60, message = "Name should be between 2 and 60 characters")
    private String name;

    @NotEmpty(message = "country can't be empty")
    private String country;
    @NotEmpty(message = "region can't be empty")
    private String region;
    @NotEmpty(message = "city can't be empty")
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
        date = dateFormat.format(new Date());
    }

    public DateIdea(DateIdeaEntity dateIdeaEntity) {
        this.id = dateIdeaEntity.getId();
        this.date = dateFormat.format(dateIdeaEntity.getDate());
        this.name = dateIdeaEntity.getName();
        this.country = dateIdeaEntity.getCountry().getName();
        this.region = dateIdeaEntity.getRegion().getName();
        this.city = dateIdeaEntity.getCity().getName();
        this.address = dateIdeaEntity.getAddress();
        this.type = dateIdeaEntity.getType().getName();
        this.minPrice = dateIdeaEntity.getMinPrice();
        this.maxPrice = dateIdeaEntity.getMaxPrice();
        this.description = dateIdeaEntity.getDescription();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DateIdea.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("date=" + date)
                .add("name='" + name + "'")
                .add("country='" + country + "'")
                .add("region='" + region + "'")
                .add("city='" + city + "'")
                .add("address='" + address + "'")
                .add("type='" + type + "'")
                .add("minPrice=" + minPrice)
                .add("maxPrice=" + maxPrice)
                .add("description='" + description + "'")
                .toString();
    }
}
