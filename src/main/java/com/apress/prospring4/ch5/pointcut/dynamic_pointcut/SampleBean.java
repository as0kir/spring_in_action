package com.apress.prospring4.ch5.pointcut.dynamic_pointcut;

public class SampleBean {
    public void foo(int x){
        System.out.println("Invoked foo() with: " + x);
    }

    public void bar(){
        System.out.println("Invoked bar()");
    }
}
