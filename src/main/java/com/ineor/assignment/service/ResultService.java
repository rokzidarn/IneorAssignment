package com.ineor.assignment.service;

import com.ineor.assignment.controller.ResultController;
import com.ineor.assignment.model.Country;
import com.ineor.assignment.model.PopulationComparator;
import com.ineor.assignment.model.Result;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Slf4j
@Service("resultService")
public class ResultService {

    private static final Logger logger = LoggerFactory.getLogger(ResultController.class);

    public Result processData(String[] arr) {
        List<Country> all = new ArrayList<>();

        String[] countryCodes = Locale.getISOCountries();  // needed for country filter
        ArrayList<String> official = new ArrayList<>();
        for (String countryCode : countryCodes) {
            official.add(new Locale("", countryCode).getDisplayCountry());
        }

        for (int i=0; i<arr.length; i++) {
            String data = JsonPath.read(arr[i], "$.[1]").toString();  // each page
            int num = JsonPath.read(data, "$.length()");  // number of countries on current page

            for (int j=0; j<num; j++) {
                try {
                    String name = JsonPath.read(data, "$.[" + j + "].country.value").toString();  // get data
                    String population = JsonPath.read(data, "$.[" + j + "].value").toString();

                    if (official.contains(name)) {  // filter out regions, continents
                        Country c = new Country(name, Long.parseLong(population));
                        all.add(c);
                    }

                } catch (Exception e) {
                    log.error("ERROR: " + (i+1) + "/" + j);  // two errors, null values for population
                }
            }
        }

        Collections.sort(all, new PopulationComparator());  // sort by population numbers, Long type

        List<Country> lowestPopulation = all.subList(0, 3);  // select top 3 in each category
        List<Country> highestPopulation = all.subList(all.size()-3, all.size());
        Collections.reverse(highestPopulation);

        return new Result(highestPopulation, lowestPopulation);
    }
}
