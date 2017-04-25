package com.example.danae.wat2340.Model;

/**
 * Created by danae on 4/25/2017.
 */

public class Data implements Comparable {
    public int x;
    public int y;
    public Data(int x, int y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public int compareTo(Object o) {
        Data d = (Data) o;
        return x-d.x;
    }
}
