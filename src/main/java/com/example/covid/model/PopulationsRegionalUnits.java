package com.example.covid.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "Population_regional_units")
public class PopulationsRegionalUnits {

    @Id
    @Column(name = "Area_Id")
    private int areaId;

    @Column(name = "Regional_Unit")
    private String regionalUnit;

    @Column(name = "Population")
    private int population;

    @OneToMany(targetEntity = CovidStats.class, cascade = CascadeType.ALL, mappedBy = "areaId")
    private List<CovidStats> covidStatsList;

}
