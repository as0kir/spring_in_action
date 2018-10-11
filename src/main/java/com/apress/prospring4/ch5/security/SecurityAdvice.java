package com.apress.prospring4.ch5.security;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class SecurityAdvice implements MethodBeforeAdvice {

    private SecurityManager securityManager;

    public SecurityAdvice() {
        this.securityManager = new SecurityManager();
    }

    public void before(Method method, Object[] objects, Object o) throws Throwable {
        UsersInfo user = securityManager.getLoggedOnUser();

        if(user == null){
            System.out.println("User not authenticated");
            throw new SecurityException("You must login before attempting to invoke the method: " + method.getName());
        }
        else if("chris".equals(user.getUserName())){
            System.out.println("Logged in user is chris - OKAY!");
        }
        else {
            System.out.println("Logged in user is " + user.getUserName() + " NOT GGOD :(");
            throw new SecurityException("User " + user.getUserName() + " is not allowed access to method " + method.getName());
        }
    }
}
