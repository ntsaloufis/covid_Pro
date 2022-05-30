package com.example.covid.controller;

import com.example.covid.model.CovidStats;
import com.example.covid.model.PopulationsRegionalUnits;
import com.example.covid.repository.CovidStatsRepository;
import com.example.covid.repository.PopulationsRegionalUnitsRepository;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class UploadCSVController {

    private PopulationsRegionalUnitsRepository populationsRegionalUnitsRepository;
    private CovidStatsRepository covidStatsRepository;

    public UploadCSVController(PopulationsRegionalUnitsRepository populationsRegionalUnitsRepository, CovidStatsRepository covidStatsRepository) {
        this.populationsRegionalUnitsRepository = populationsRegionalUnitsRepository;
        this.covidStatsRepository = covidStatsRepository;
    }

    @PostMapping("/upload-populations-regional-units")
    public String uploadPopulationsRegionalUnitsData(@RequestParam("file") MultipartFile file) throws Exception {
        List<PopulationsRegionalUnits> populationsRegionalUnitsList = new ArrayList<>();
        InputStream inputStream = file.getInputStream();
        CsvParserSettings setting = new CsvParserSettings();
        setting.setHeaderExtractionEnabled(true);
        CsvParser parser = new CsvParser(setting);
        List<Record> parsePopulationsRegionalUnitsRecords = parser.parseAllRecords(inputStream);
        parsePopulationsRegionalUnitsRecords.forEach(record -> {
            PopulationsRegionalUnits populationsRegionalUnits = new PopulationsRegionalUnits();
            populationsRegionalUnits.setAreaId(Integer.parseInt(record.getString("Id")));
            populationsRegionalUnits.setRegionalUnit(record.getString("Regional Unit"));
            populationsRegionalUnits.setPopulation(Integer.parseInt(record.getString("Population").replace(".", "")));
            populationsRegionalUnitsList.add(populationsRegionalUnits);
        });
        populationsRegionalUnitsRepository.saveAll(populationsRegionalUnitsList);
        return "Upload populationsRegionalUnits.csv successfully";
    }

    @PostMapping("/upload-covid-stats")
    public String uploadCovidStatsData(@RequestParam("file") MultipartFile file) throws Exception {
        List<CovidStats> covidStatsList = new ArrayList<>();
        InputStream inputStream = file.getInputStream();
        CsvParserSettings setting = new CsvParserSettings();
        setting.setHeaderExtractionEnabled(true);
        CsvParser parser = new CsvParser(setting);
        List<Record> parseCovidStatsRecords = parser.parseAllRecords(inputStream);
        parseCovidStatsRecords.forEach(record -> {
            CovidStats covidStats = new CovidStats();
            covidStats.setArea(record.getString("area"));
            covidStats.setAreaId(covidStatsRepository.findByAreaId(record.getInt("areaid")));
            covidStats.setDailyDose1(Integer.parseInt(record.getString("dailydose1")));
            covidStats.setDailyDose2(Integer.parseInt(record.getString("dailydose2")));
            covidStats.setDayDiff(Integer.parseInt(record.getString("daydiff")));
            covidStats.setDayTotal(Integer.parseInt(record.getString("daytotal")));
            try {
                Date date1 = new SimpleDateFormat("YYYY-MM-DDTHH:MM:SS").parse(record.getString("referencedate"));
                covidStats.setReferenceDate(date1);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            covidStats.setTotalDistinctPerson(Integer.parseInt(record.getString("totaldistinctpersons")));
            covidStats.setTotalDose1(Integer.parseInt(record.getString("totaldose1")));
            covidStats.setTotalDose2(Integer.parseInt(record.getString("totaldose2")));
            covidStats.setTotalVaccinations(Integer.parseInt(record.getString("totalvaccinations")));
            covidStatsList.add(covidStats);
        });
        covidStatsRepository.saveAll(covidStatsList);
        return "Upload covidStats.csv successfully";
    }
}



