package com.apress.prospring4.ch5.after;

import org.springframework.aop.framework.ProxyFactory;

public class AfterAdviceExample {
    public static void main(String[] args) {

        KeyGenerator generator = getKeyGenerator();
        for (int i = 0; i < 10; i++) {
            try {
                long key = generator.getKey();
                System.out.println("Key: " + key);
            }catch (SecurityException ex){
                System.out.println("Weak Key generated");
            }
        }
    }

    private static KeyGenerator getKeyGenerator(){
        ProxyFactory pf = new ProxyFactory();
        pf.addAdvice(new WeakKeyCheckAdvice());
        pf.setTarget(new KeyGenerator());
        return (KeyGenerator) pf.getProxy();
    }
}
