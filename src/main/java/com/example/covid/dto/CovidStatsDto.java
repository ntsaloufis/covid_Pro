package com.example.covid.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class CovidStatsDto {

    private final int totalVaccinations;

    private final Date referenceDate;

}
