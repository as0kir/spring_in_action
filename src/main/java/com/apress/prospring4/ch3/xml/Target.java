package com.apress.prospring4.ch3.xml;

import com.apress.prospring4.ch3.Bar;
import com.apress.prospring4.ch3.Foo;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Target {

    private Foo foo;
    private Foo foo2;
    private Bar bar;

    public Target() {
    }

    public Target(Foo foo) {
        this.foo = foo;
        System.out.println("Target(Foo foo) called");
    }

    public Target(Foo foo, Bar bar) {
        this.foo = foo;
        this.bar = bar;
        System.out.println("Target(Foo foo, Bar bar) called");
    }

    public void setFoo(Foo foo) {
        this.foo = foo;
        System.out.println("setFoo(Foo foo) called");
    }

    public void setFoo2(Foo foo2) {
        this.foo2 = foo2;
        System.out.println("setFoo2(Foo foo2) called");
    }

    public void setBar(Bar bar) {
        this.bar = bar;
        System.out.println("setBar(Bar bar) called");
    }

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/ch3/app-context-auto-xml.xml");
        ctx.refresh();

        Target t = null;
        System.out.println("Using byName:\n");
        t = (Target)ctx.getBean("targetByName");

        System.out.println("\nUsing byType:\n");
        t = (Target)ctx.getBean("targetByType");

        System.out.println("\nUsing constructor:\n");
        t = (Target)ctx.getBean("targetConstructor");

    }

}
