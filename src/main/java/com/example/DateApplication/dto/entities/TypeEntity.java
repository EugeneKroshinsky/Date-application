package com.example.DateApplication.dto.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "types")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TypeEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "type")
    private List<DateIdeaEntity> dateIdeaEntities;
}
