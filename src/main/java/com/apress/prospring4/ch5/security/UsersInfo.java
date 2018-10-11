package com.apress.prospring4.ch5.security;

public class UsersInfo {
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public UsersInfo(String userName, String password) {

        this.userName = userName;
        this.password = password;
    }
}
