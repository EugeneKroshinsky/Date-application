package com.example.DateApplication.dto.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name="date_idea")
@Setter
@Getter
@AllArgsConstructor
public class DateIdeaEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="date_of_creation")
    private Date date;

    @Column(name = "name")
    @NotEmpty(message = "Name can't be empty")
    @Size(min=2, max=60, message = "Name should be between 2 and 60 characters")
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private CountryEntity country;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private RegionEntity region;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private CityEntity city;

    @Column(name = "address")
    @NotEmpty(message = "address can't be empty")
    @Size(min=2, max=60, message = "address should be between 2 and 60 characters")
    private String address;

    @ManyToOne
    @JoinColumn(name = "type_id")
    @NotEmpty(message = "type can't be empty")
    @Size(min=2, max=60, message = "type should be between 2 and 60 characters")
    private TypeEntity type;

    @Column(name="minprice")
    @Min(value=0, message = "minPrice should be greater than 0")
    private int minPrice;

    @Column(name="maxprice")
    @Min(value=0, message = "minPrice should be greater than 0")
    private int maxPrice;

    @Column(name="description")
    @NotEmpty(message = "description can't be empty")
    private String description;

    public DateIdeaEntity() {
        date = new Date();
    }
}
