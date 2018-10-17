package com.apress.prospring4.ch5.introduction;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;

public class IntroductionExample {
    public static void main(String[] args) {
        TargetBean targetBean = new TargetBean();
        targetBean.setName("Chris Schaefer");

        Advisor advisor = new IsModifiedAdvisor();

        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(targetBean);
        pf.addAdvisor(advisor);
        pf.setOptimize(true);

        TargetBean proxy = (TargetBean) pf.getProxy();
        IsModified proxyInterface = (IsModified) proxy;

        System.out.println("IsTargetBean?: " + (proxy instanceof TargetBean));
        System.out.println("IsModified?:   " + (proxy instanceof IsModified));
        System.out.println("Has been modified?: " + proxyInterface.isModified());

        proxy.setName("Chris Schaefer");
        System.out.println("Has been modified?: " + proxyInterface.isModified());

        proxy.setName("Chris Schaeferr");
        System.out.println("Has been modified?: " + proxyInterface.isModified());
    }
}
