package com.example.covid.repository;

import com.example.covid.dto.CovidStatsDto;
import com.example.covid.model.CovidStats;
import com.example.covid.model.PopulationsRegionalUnits;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CovidStatsRepository extends CrudRepository<CovidStats, Integer> {

    PopulationsRegionalUnits findByAreaId (Integer areaId);

    List<CovidStats> findByArea(String area);
}

