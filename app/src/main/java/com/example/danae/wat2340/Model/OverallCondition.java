package com.example.danae.wat2340.Model;

/**
 * Created by Minky on 2017-04-24.
 */

public enum OverallCondition {
    SAFE("safe"),
    TREATABLE("treatable"),
    UNSAFE("unsafe");

    private String overallCondition;
    OverallCondition(String overallCondition) {this.overallCondition = overallCondition;}
    public String toString() {return "" + overallCondition;}
}
