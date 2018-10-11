package com.apress.prospring4.ch5.around;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;

public class ProfilingInterceptor implements MethodInterceptor {

    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start(methodInvocation.getMethod().getName());
        Object returnValue = methodInvocation.proceed();
        sw.stop();
        dumpInfo(methodInvocation, sw.getTotalTimeMillis());
        return returnValue; 

    }

    private void dumpInfo(MethodInvocation methodInvocation, long totalTimeMillis) {
        Method m = methodInvocation.getMethod();
        Object target = methodInvocation.getThis();
        Object[] args = methodInvocation.getArguments();

        System.out.println("Executed method: " + m.getName());
        System.out.println("On object of type " + target.getClass().getName());
        System.out.println("With arguments: ");
        for (int i = 0; i < args.length; i++) {
            System.out.print("    > " + args[i]);
        }
        System.out.print("\n");
        System.out.println("Took: " + totalTimeMillis + " ms");
    }
}
