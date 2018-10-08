package com.apress.prospring4.ch3;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

public class Main {

    public static void main(String[] args) {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("META-INF/spring/ch3/spring-config.xml"));
        Oracle oracle = (Oracle)factory.getBean("oracle");
        System.out.println(oracle.defineMeaningOfLife());
    }
}
