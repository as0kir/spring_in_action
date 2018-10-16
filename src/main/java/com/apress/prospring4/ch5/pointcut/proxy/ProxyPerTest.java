package com.apress.prospring4.ch5.pointcut.proxy;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class ProxyPerTest {
    public static void main(String[] args) {
        SimpleBean target = new DefaultSimpleBean();
        Advisor advisor = new DefaultPointcutAdvisor(new TestPointcut(), new NoOpBeforeAdvice());

        runCglibTests(advisor, target);
        runCglibFrozenTests(advisor, target);
        runJdkTests(advisor, target);
    }

    private static void runJdkTests(Advisor advisor, SimpleBean target) {
        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(target);
        pf.addAdvisor(advisor);
        pf.setInterfaces(new Class[]{SimpleBean.class});

        SimpleBean proxy = (SimpleBean) pf.getProxy();
        System.out.println("Running JDK Tests");
        test(proxy);
    }

    private static void runCglibFrozenTests(Advisor advisor, SimpleBean target) {
        ProxyFactory pf = new ProxyFactory();
        pf.setProxyTargetClass(true);
        pf.setTarget(target);
        pf.addAdvisor(advisor);
        pf.setFrozen(true);

        SimpleBean proxy = (SimpleBean) pf.getProxy();
        System.out.println("Running CGLIB (Frozen) Tests");
        test(proxy);
    }

    private static void runCglibTests(Advisor advisor, SimpleBean target) {
        ProxyFactory pf = new ProxyFactory();
        pf.setProxyTargetClass(true);
        pf.setTarget(target);
        pf.addAdvisor(advisor);

        SimpleBean proxy = (SimpleBean) pf.getProxy();
        System.out.println("Running CGLIB (Standard) Tests");
        test(proxy);
    }

    private static void test(SimpleBean bean){
        long before = 0;
        long after = 0;
        //  Тестирование метода, снабженного советом
        System.out .println( "Testing Advised Method");
        before = System.currentTimeMillis();
        for (int х = 0;  х < 500000; х++) {
            bean.advised();
        }
        after =  System.currentTimeMillis();
        //  Вывод времени выполнения метода
        System.out.println("Took " + (after - before) + "ms");

        //  Тестирование метода,  не  снабженного  советом
        System.out.println("Testing  Unadvised Method");
        before = System.currentTimeMillis();
        for (int x = 0;  x  < 500000; x++) {
            bean.unadvised();
        }
        after = System.currentTimeMillis();
        //  Вывод времени выполнения метода
        System.out.println("Took " + (after - before) + "ms");

        //  Тестирование метода equals()
        System.out.println("Testing equals() Method");
        before = System.currentTimeMillis();
        for(int x = 0;  x < 500000; x++) {
            bean.equals(bean);
        }
        after = System.currentTimeMillis();
        //  Вывод времени выполнения метода
        System.out.println("Took " + (after - before) + "ms");

        // Тестирование  метода hashCode()
        System.out.println("Testing hashCode () Method") ;
        before = System.currentTimeMillis();
        for (int x = 0; x < 500000; x++) {
            bean.hashCode();
        }
        after = System.currentTimeMillis();
        //  Вывод времени выполнения метода
        System.out.println("Took " + (after - before) + "ms");

        Advised advised = (Advised)bean;

        //  Тестирование метода Advised.getProxyTargetClass()
        System.out. println( "Testing Advised.getProxyTargetClass ()  Method") ;
        before = System.currentTimeMillis();
        for(int x = 0; x < 500000; x++) {
            advised.getTargetClass();
        }
        after = System.currentTimeMillis();
        //  Вывод времени выполнения метода
        System.out.println("Took "  +   (after - before) +  "ms");
        System.out.println(">>>\n");
    }
}
