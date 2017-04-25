package com.example.danae.wat2340.Model;

import java.io.Serializable;

/**
 * Created by danae on 4/24/2017.
 */

public enum Virus implements Serializable {
    NONE("None"),
    NOROVIRUS("Norovirus"),
    ROTAVIRUS("Rotavirus"),
    HEPATITIS("Hepititus A");

    private String virus;
    Virus(String virus) {
        this.virus = virus;
    }
    public String toString() {
        return ""+ virus;
    }


}
