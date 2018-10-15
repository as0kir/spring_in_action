package com.apress.prospring4.ch5.pointcut.static_pointcut;

import com.apress.prospring4.ch5.pointcut.SimpleAdvice;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class StaticPointcutSimple {
    public static void main(String[] args) {
        BeanOne beanOne = new BeanOne();
        BeanTwo beanTwo = new BeanTwo();

        Pointcut pc = new SimpleStaticPointcut();
        Advice advice = new SimpleAdvice();
        Advisor advisor = new DefaultPointcutAdvisor(pc, advice);

        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(beanOne);
        pf.addAdvisor(advisor);
        BeanOne proxyOne = (BeanOne) pf.getProxy();

        pf = new ProxyFactory();
        pf.setTarget(beanTwo);
        pf.addAdvisor(advisor);
        BeanTwo proxyTwo = (BeanTwo) pf.getProxy();

        proxyOne.foo();
        proxyTwo.foo();

        proxyOne.bar();
        proxyTwo.bar();
    }
}
