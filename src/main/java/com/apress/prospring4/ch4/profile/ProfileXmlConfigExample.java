package com.apress.prospring4.ch4.profile;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

public class ProfileXmlConfigExample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/ch4/profile/*-config.xml");
        ctx.refresh();

        FoodProviderService service = (FoodProviderService) ctx.getBean("foodProviderService");
        List<Food> foods = service.provideLunchSet();
        for (Food food:foods){
            System.out.println("Food: "+ food.getName());
        }
    }
}
