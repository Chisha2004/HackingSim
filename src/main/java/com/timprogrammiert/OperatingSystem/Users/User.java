package com.timprogrammiert.OperatingSystem.Users;

/**
 * Author : Tim
 * Date: 28.01.2024
 * Version: 1.0
 */
public class User {
    String password;
    String userName;
    public User(String password, String userName){
        this.password = password;
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }
}
