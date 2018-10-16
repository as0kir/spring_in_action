package com.apress.prospring4.ch5.pointcut.annotation_pointcut;

public class SampleAnnotationBean  {

    @AdviceRequired
    public void foo(int x){
        System.out.println("Invoked foo() with "+x);
    }

    public void bar(){
        System.out.println("Invoked bar()");
    }
}
