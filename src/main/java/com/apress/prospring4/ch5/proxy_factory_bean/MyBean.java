package com.apress.prospring4.ch5.proxy_factory_bean;

public class MyBean {
    private MyDependency dependency;

    public void execute(){
        dependency.foo();
        dependency.bar();
    }

    public void setDependency(MyDependency dependency) {
        this.dependency = dependency;
    }
}
