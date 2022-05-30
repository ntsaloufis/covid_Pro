package com.example.covid.repository;

import com.example.covid.model.PopulationsRegionalUnits;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PopulationsRegionalUnitsRepository extends CrudRepository<PopulationsRegionalUnits, Integer> {

    Optional<PopulationsRegionalUnits> findByRegionalUnit(String region);


}
