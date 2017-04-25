package com.example.danae.wat2340.Model;

/**
 * Created by danae on 4/24/2017.
 */


public class Location {
    private double _latitude;
    private double _longitude;
    private String fullAddress;

    public Location() {

    }

    /**
     * creates Location with latitude and longitude
     * @param lat latitude
     * @param longitude longitude
     */
    public Location(double lat, double longitude) {
        _latitude = lat;
        _longitude = longitude;
    }

    /**
     * getter for latitude
     * @return latitude
     */
    public double getLatitude() { return _latitude; }
    /**
     * getter for longitude
     * @return longitude
     */
    public double getLongitude() { return _longitude; }

    /**
     * setter for latitude
     * @param lat latitude
     */
    public void set_latitude(double lat) {
        this._latitude = lat;
    }

    /**
     * setter for longitude
     * @param _longitude longitude
     */
    public void set_longitude(double _longitude) {
        this._longitude = _longitude;
    }


    /**
     * toString method
     * @return String form of latitude and longitude
     */
    public String toString() {
        return "" + getLatitude() + "," + getLongitude();
    }

}
