package com.apress.prospring4.ch3;

import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * Created by kirillov_av on 05.10.2018.
 */
public class HierarchicalAppContextUsage {
    public static void main(String[] args) {
        GenericXmlApplicationContext parent = new GenericXmlApplicationContext();
        parent.load("classpath:META-INF/spring/parent.xml");
        parent.refresh();
        GenericXmlApplicationContext child = new GenericXmlApplicationContext();
        child.load("classpath:META-INF/spring/app-context_hier-xml.xml");
        child.setParent(parent);
        child.refresh();
        SimpleTarget targetl = (SimpleTarget) child.getBean("targetl");
        SimpleTarget target2 = (SimpleTarget) child.getBean("target2");
        SimpleTarget target3 = (SimpleTarget) child.getBean("target3");

        System.out.println(targetl.getVal());
        System.out.println(target2.getVal());
        System.out.println(target3.getVal());
    }
}
