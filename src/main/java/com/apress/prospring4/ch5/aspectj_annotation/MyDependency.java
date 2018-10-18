package com.apress.prospring4.ch5.aspectj_annotation;

import org.springframework.stereotype.Component;

@Component("myDependency")
public class MyDependency {
    public void foo(int value){
        System.out.println("foo(int): " + value);
    }

    public void bar(){
        System.out.println("bar()");
    }
}
