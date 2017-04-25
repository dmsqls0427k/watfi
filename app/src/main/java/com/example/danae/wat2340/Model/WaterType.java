package com.example.danae.wat2340.Model;

/**
 * Created by Minky on 2017-04-24.
 */

public enum WaterType {
    BOTTLED("Bottled"),
    WELL("Well"),
    STREAM("Stream"),
    LAKE("Lake"),
    SPRING("Spring"),
    OTHER("Other");
    private String waterType;
    WaterType(String waterType) {
        this.waterType = waterType;
    }
    public String toString() {return "" + waterType;}
}
