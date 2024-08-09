package com.example.DateApplication.dto.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "country")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CountryEntity {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "country")
    private List<RegionEntity> regions;

    @OneToMany(mappedBy = "country")
    private List<DateIdeaEntity> dateIdeaEntities;
}
