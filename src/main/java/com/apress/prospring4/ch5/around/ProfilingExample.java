package com.apress.prospring4.ch5.around;

import org.springframework.aop.framework.ProxyFactory;

public class ProfilingExample {
    public static void main(String[] args) {
        WorkerBean bean = getWorkerBean();
        bean.doSomeWork(1000000);
    }

    private static WorkerBean getWorkerBean() {
        WorkerBean bean = new WorkerBean();
        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(bean);
        pf.addAdvice(new ProfilingInterceptor());

        return (WorkerBean) pf.getProxy();
    }
}
