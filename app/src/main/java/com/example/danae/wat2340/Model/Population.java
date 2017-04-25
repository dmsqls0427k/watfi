package com.example.danae.wat2340.Model;

/**
 * Created by Minky on 2017-04-24.
 */

public enum Population {
    CROWDED("crowded"),
    FEW("few"),
    SEVERAL("several"),
    NO_HUMAN_AROUND("no human around");

    private String population;

    Population(String population) {
        this.population = population;
    }

    public String toString() {
        return "" + population;
    }
}
