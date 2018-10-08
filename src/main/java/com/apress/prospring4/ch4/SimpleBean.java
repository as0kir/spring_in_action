package com.apress.prospring4.ch4;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SimpleBean {
    private static final String DEFAULT_NAME = "Luke Skywolker";
    private String name;
    private int age = Integer.MIN_VALUE;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void init(){
        System.out.println("Initializing bean");
        if(name == null){
            System.out.println("Using default name");
            name = DEFAULT_NAME;
        }

        if (age == Integer.MIN_VALUE){
            throw new  IllegalArgumentException("You must set the age property of any beans of type "+ SimpleBean.class);
        }
    }

    @Override
    public String toString() {
        return "SimpleBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/ch4/app-context-xml.xml");
        ctx.refresh();

        SimpleBean bean1 = getBean("simpleBean1", ctx);
        SimpleBean bean2 = getBean("simpleBean2", ctx);
        SimpleBean bean3 = getBean("simpleBean3", ctx);
    }

    public static SimpleBean getBean(String beanName, ApplicationContext ctx){
        try{
            SimpleBean bean = (SimpleBean) ctx.getBean(beanName);
            System.out.println(bean);
            return bean;
        }
        catch (BeanCreationException ex){
            System.out.println("An error ocuured in bean configuration: " + ex.getMessage());
            return null;
        }
    }
}
