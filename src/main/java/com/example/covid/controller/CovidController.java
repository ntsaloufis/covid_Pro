package com.example.covid.controller;

import com.example.covid.dto.CovidStatsDto;
import com.example.covid.dto.PopulationPerRegionResponseDto;
import com.example.covid.model.CovidStats;
import com.example.covid.repository.CovidStatsRepository;
import com.example.covid.repository.PopulationsRegionalUnitsRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class CovidController {

    private final PopulationsRegionalUnitsRepository populationsRegionalUnitsRepository;

    private final CovidStatsRepository covidStatsRepository;

    public CovidController(PopulationsRegionalUnitsRepository populationsRegionalUnitsRepository, CovidStatsRepository covidStatsRepository) {
        this.populationsRegionalUnitsRepository = populationsRegionalUnitsRepository;
        this.covidStatsRepository = covidStatsRepository;
    }

    @PostMapping("/population-per-region")
    public PopulationPerRegionResponseDto getPopulationPerRegion(@RequestParam("region") String region) {

        var populationOptional = populationsRegionalUnitsRepository.findByRegionalUnit(region);
        if (populationOptional.isEmpty()) {
            throw new IllegalArgumentException("No region");
        }

        return new PopulationPerRegionResponseDto(populationOptional.get()
                .getPopulation());

    }

    @PostMapping("/vaccinations-and-date-rer-area")
    public List<CovidStatsDto> getVaccinationsAndDatePerArea(@RequestParam("area") String area) {

        List<CovidStatsDto> covidStatsList = new ArrayList<>();
        for (final CovidStats covidStats : covidStatsRepository.findByArea(area)) {
            covidStatsList.add(new CovidStatsDto(covidStats.getTotalVaccinations(), covidStats.getReferenceDate()));
        }

        if (covidStatsList.isEmpty()) {
            return Collections.emptyList();
        }

        return covidStatsList;

    }


}
