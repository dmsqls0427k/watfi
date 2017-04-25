package com.example.danae.wat2340.Model;


/**
 * Created by danae on 4/24/2017.
 */

public class PurityReport {
    private String date;
    private int id;
    private String name;
    private String location;
    private String condition;
    private double virusPPM;
    private double contaminantPPM;
    private String virus;

    /**
     * Empty Constructor
     */
    public PurityReport() {

    }

    /**
     * constructor for purity report
     * @param date date published
     * @param id id number of report
     * @param name name of publisher
     * @param location location of the water
     * @param condition condition of the water
     * @param virusPPM measured virus ppm of water
     * @param contaminantPPM measured contaminant ppm of water
     */
    public PurityReport(String date, int id, String name,
                        String location, String condition, String virus, double virusPPM,
                        double contaminantPPM) {
        this.date = date;
        this.id = id;
        this.name = name;
        this.virus = virus;
        this.location = location;
        this.condition = condition;
        this.virusPPM = virusPPM;
        this.contaminantPPM = contaminantPPM;
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

    public String getLocation() {
        return location;
    }
    public String getCondition() {
        return condition;
    }

    public double getVirusPPM() {
        return virusPPM;
    }


    public double getContaminantPPM() {
        return contaminantPPM;
    }

    public String toString() {
        return id + " " + location;
    }


}
