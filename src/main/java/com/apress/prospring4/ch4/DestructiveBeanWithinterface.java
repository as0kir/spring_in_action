package com.apress.prospring4.ch4;

import org.springframework.context.support.GenericXmlApplicationContext;
import sun.security.krb5.internal.crypto.Des;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;

public class DestructiveBeanWithinterface {
    private File file;
    private String filePath;

    @PostConstruct
    public void afterPropertiesSet() throws Exception{
        System.out.println("Initializing bean");

        if(filePath == null){
            throw new IllegalArgumentException("You must specify the filePath property of " + DestructiveBeanWithinterface.class);
        }

        this.file = new File(filePath);
        this.file.createNewFile();

        System.out.println("File exists: "+file.exists());
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @PreDestroy
    public void destroy(){
        System.out.println("Destroying bean");

        if(!file.delete()){
            System.err.println("ERROR: failed to delete file");
        }
        System.out.println("File exists: "+file.exists());
    }

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/ch4/app-context-annotation.xml");
        ctx.registerShutdownHook();
        ctx.refresh();

        DestructiveBeanWithinterface beanWithinterface = (DestructiveBeanWithinterface) ctx.getBean("destractiveBean");
    }
}
