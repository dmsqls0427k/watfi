package com.example.danae.wat2340.Model;

/**
 * Created by Minky on 2017-04-24.
 */

public enum WaterCondition {
    WASTE("Waste"),
    TRETABLECLEAR("Treatable-Clear"),
    TREATABLEMUDDY("Treatable-Muddy"),
    PORTABLE("Portable");
    private String waterCondition;
    WaterCondition(String waterCondition) {this.waterCondition = waterCondition;}
    public String toString() {return "" + waterCondition;}
}
