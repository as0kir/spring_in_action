package com.apress.prospring4.ch5.pointcut.proxy;

public class DefaultSimpleBean implements SimpleBean {
    private long dummy = 0;

    public void advised() {
        dummy = System.currentTimeMillis();
    }

    public void unadvised() {
        dummy = System.currentTimeMillis();
    }
}
