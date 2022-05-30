package com.example.covid.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "Covid_Stats")
public class CovidStats {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="Id")
    private int id;

    @Column(name = "Area")
    private String area;

    @ManyToOne
    @JoinColumn(name = "areaId")
    private PopulationsRegionalUnits areaId;

    @Column(name = "Daily_dose_1")
    private int dailyDose1;

    @Column(name = "Daily_dose_2")
    private int dailyDose2;

    @Column(name = "Daily_diff")
    private int dayDiff;

    @Column(name = "Daily_total")
    private int dayTotal;

    @Column(name = "Reference_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date referenceDate;

    @Column(name = "Total_distinct_person")
    private int totalDistinctPerson;

    @Column(name = "Total_dose_1")
    private int totalDose1;

    @Column(name = "Total_dose_2")
    private int totalDose2;

    @Column(name = "Total_vaccinations")
    private int totalVaccinations;

}
