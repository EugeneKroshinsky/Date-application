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
import java.util.StringJoiner;

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
    private String address;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private TypeEntity type;

    @Column(name="minprice")
    private int minPrice;

    @Column(name="maxprice")
    private int maxPrice;

    @Column(name="description")
    private String description;

    public DateIdeaEntity() {
        date = new Date();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DateIdeaEntity.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("date=" + date)
                .add("name='" + name + "'")
                .add("country=" + country)
                .add("region=" + region)
                .add("city=" + city)
                .add("address='" + address + "'")
                .add("type=" + type)
                .add("minPrice=" + minPrice)
                .add("maxPrice=" + maxPrice)
                .add("description='" + description + "'")
                .toString();
    }
}
