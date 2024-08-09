package com.example.DateApplication.dto.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="region")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegionEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    private CountryEntity country;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "region")
    private List<DateIdeaEntity> dateIdeaEntities;

    @OneToMany(mappedBy = "region")
    private List<CityEntity> cities;

    public RegionEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
