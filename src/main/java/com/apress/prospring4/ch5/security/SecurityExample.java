package com.apress.prospring4.ch5.security;

import org.springframework.aop.framework.ProxyFactory;

public class SecurityExample {
    public static void main(String[] args) {
        SecurityManager sm = new SecurityManager();
        SecureBean bean = getSecureBean();
        sm.login("chris", "pwd");
        bean.writeSecureMessage();
        sm.logout();

        try {
            sm.login("invalid user", "ped");
            bean.writeSecureMessage();
        }
        catch (SecurityException ex){
            System.out.println("Exception caught :" + ex.getMessage());
        }
        finally {
            sm.logout();
        }
        try {
            bean.writeSecureMessage();
        }
        catch (SecurityException ex){
            System.out.println("Exception caught :" + ex.getMessage());
        }
    }

    private static SecureBean getSecureBean(){
        SecureBean target = new SecureBean();
        SecurityAdvice advice = new SecurityAdvice();
        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(target);
        pf.addAdvice(advice);

        return (SecureBean) pf.getProxy();
    }
}
