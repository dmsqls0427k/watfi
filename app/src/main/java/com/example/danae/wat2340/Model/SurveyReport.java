package com.example.danae.wat2340.Model;

/**
 * Created by Minky on 2017-04-24.
 */

public class SurveyReport {
    private String date;
    private int id;
    private String name;
    private Population population;
    private String longitude;
    private String latitude;
    private String waterType;

    public SurveyReport() {

    }

    public SurveyReport(String date, int id, String name, Population population, String longitude,
                        String latitude, String waterType) {
        this.date = date;
        this.name = name;
        this.id = id;
        this.population = population;
        this.longitude = longitude;
        this.latitude = latitude;
        this.waterType = waterType;
    }
    public String toString() {
        return "" + id + ": " + name;
    }

    public String getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Population getPopulation() {
        return population;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getWaterType() {
        return waterType;
    }
}
