package com.example.danae.wat2340.Model;



/**
 * Created by danae on 4/24/2017.
 */

public class SourceReport {
    private String date;
    private int id;
    private String name;
    private String longitude;
    private String latitude;
    private String condition;
    private String waterType;

    /**
     * Empty Constructor
     */
    public SourceReport() {
    }

    /**
     * constructor for source report
     * @param date date that report was added
     * @param id id number of report
     * @param name name of user
     * @param latitude the location of water report
     * @param longitude the longitude of water report
     * @param condition condition of water
     * @param waterType type of water
     */
    public SourceReport(String date, int id, String name,
                        String latitude, String longitude, String condition,
                        String waterType) {
        this.date = date;
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.condition = condition;
        this.waterType = waterType;
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
    public String getLatitude() {return latitude;}
    public String getLongitude() {
        return longitude;
    }
    public String getCondition() {
        return condition;
    }
    public String getWaterType() {
        return waterType;
    }
    public void setWaterType(String waterType) {
        this.waterType = waterType;
    }

    public String toString() {
        return "" + id + " : " + name + " latitude: " + latitude;
    }
}
