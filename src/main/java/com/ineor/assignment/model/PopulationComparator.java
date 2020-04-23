package com.ineor.assignment.model;

import java.util.Comparator;

public class PopulationComparator implements Comparator<Country> {

    public int compare(Country c1, Country c2) {
        return c1.getPopulation().compareTo(c2.getPopulation());
    }
}