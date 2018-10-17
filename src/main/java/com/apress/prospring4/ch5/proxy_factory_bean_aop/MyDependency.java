package com.apress.prospring4.ch5.proxy_factory_bean_aop;

public class MyDependency {
    public void foo(int value){
        System.out.println("foo(int): " + value);
    }

    public void bar(){
        System.out.println("bar()");
    }
}
