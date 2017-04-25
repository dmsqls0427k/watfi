package com.example.danae.wat2340.Model;

/**
 * Created by danae on 4/24/2017.
 */

public class Users {
    private String name, email, password;
    private Standing userType;

    /**
     * creates new user
     * @param name name of the user
     * @param email email used for login
     * @param password password for login
     */
    public Users(String name, String email, String password, Standing userType) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    public Users() {

    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Standing getUserType() {
        return userType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserType(Standing userType) {
        this.userType = userType;
    }

}
