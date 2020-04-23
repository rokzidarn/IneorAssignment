package com.ineor.assignment.model;

import lombok.Data;

@Data
public class Country {
    String name;
    Long population;

    public Country(String name, Long population) {
        this.name = name;
        this.population = population;
    }

    @Override
    public String toString() {
        return this.population.toString() + " / " + this.name;
    }
}
