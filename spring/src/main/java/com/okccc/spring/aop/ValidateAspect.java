package com.okccc.spring.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Author: okccc
 * Date: 2022/11/4 6:10 下午
 * Desc:
 */

@Component
@Aspect
@Order(1)
public class ValidateAspect {

    @Pointcut("execution(* com.okccc.spring.aop.*.*(..))")
    public void pointCut(){}

    @Before("pointCut()")
    public void beforeAdvice() {
        System.out.println("校验切面优先级");
    }
}
