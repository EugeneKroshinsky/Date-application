package com.example.DateApplication.dto.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="city")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CityEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "region_id", referencedColumnName = "id")
    private RegionEntity region;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "city")
    private List<DateIdeaEntity> dateIdeaEntities;
}
