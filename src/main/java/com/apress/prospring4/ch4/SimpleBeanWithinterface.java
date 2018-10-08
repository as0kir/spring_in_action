package com.apress.prospring4.ch4;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SimpleBeanWithinterface implements InitializingBean {
    private static final String DEFAULT_NAME = "Luke Skywolker";
    private String name;
    private int age = Integer.MIN_VALUE;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private void myInit(){
        System.out.println("My init");
    }

    @Override
    public String toString() {
        return "SimpleBeanWithinterface{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("Initializing bean");
        if(name == null){
            System.out.println("Using default name");
            name = DEFAULT_NAME;
        }

        if (age == Integer.MIN_VALUE){
            throw new  IllegalArgumentException("You must set the age property of any beans of type "+ SimpleBean.class);
        }
    }

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/ch4/app-context-2-xml.xml");
        ctx.refresh();

        SimpleBeanWithinterface bean1 = getBean("simpleBean1", ctx);
        SimpleBeanWithinterface bean2 = getBean("simpleBean2", ctx);
        SimpleBeanWithinterface bean3 = getBean("simpleBean3", ctx);
    }

    public static SimpleBeanWithinterface getBean(String beanName, ApplicationContext ctx){
        try{
            SimpleBeanWithinterface bean = (SimpleBeanWithinterface) ctx.getBean(beanName);
            System.out.println(bean);
            return bean;
        }
        catch (BeanCreationException ex){
            System.out.println("An error ocuured in bean configuration: " + ex.getMessage());
            return null;
        }
    }

}
