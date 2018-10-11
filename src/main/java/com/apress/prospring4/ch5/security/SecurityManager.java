package com.apress.prospring4.ch5.security;

public class SecurityManager {
    private static ThreadLocal<UsersInfo> threadLocal = new ThreadLocal<UsersInfo>();

    public void login(String userName, String password){
        threadLocal.set(new UsersInfo(userName, password));
    }

    public void logout(){
        threadLocal.set(null);
    }

    public UsersInfo getLoggedOnUser(){
        return threadLocal.get();
    }
}
