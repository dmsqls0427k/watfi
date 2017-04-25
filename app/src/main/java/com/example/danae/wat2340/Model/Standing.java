package com.example.danae.wat2340.Model;

import java.io.Serializable;

/**
 * Created by Minky on 2017-04-23.
 */

public enum Standing implements Serializable {
    USER("user"),
    WORKER("worker"),
    MANAGER("manager"),
    ADMIN("admin");

    private String standing;

    Standing(String standing) {
        this.standing = standing;
    }

    public String toString() {
        return "" + standing;
    }
}