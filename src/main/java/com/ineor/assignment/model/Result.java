package com.ineor.assignment.model;

import lombok.Data;

import java.util.List;

@Data
public class Result {
    List<Country> highest_population;
    List<Country> lowest_population;

    public Result(List<Country> highest_population, List<Country> lowest_population) {
        this.highest_population = highest_population;
        this.lowest_population = lowest_population;
    }
}
