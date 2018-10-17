package com.apress.prospring4.ch5.introduction;

import org.aopalliance.aop.Advice;
import org.springframework.aop.support.DefaultIntroductionAdvisor;

public class IsModifiedAdvisor extends DefaultIntroductionAdvisor {
    public IsModifiedAdvisor() {
        super(new IsModifiedМixin());
    }
}
