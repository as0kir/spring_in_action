package com.apress.prospring4.ch4.environment;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import java.util.HashMap;
import java.util.Map;

public class EnvironmentSample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.refresh();

        ConfigurableEnvironment env = ctx.getEnvironment();
        MutablePropertySources propertySources = env.getPropertySources();

        Map appMap = new HashMap();
        appMap.put("user.home", "application_home");
        propertySources.addFirst(new MapPropertySource("PROSPRING4_MAP", appMap));

        System.out.println("user.home: " +  System.getProperty("user.home"));
        System.out.println("JAVA_HOМE: "+  System.getenv("JAVA_HOME"));
        System.out.println("user.home: "+  env.getProperty("user.home"));
        System.out.println("JAVA_HOМE: "+  env.getProperty("JAVA_HOМE"));
        System.out.println("application.home: "+ env.getProperty("application.home"));
    }
}
