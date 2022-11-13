package com.okccc.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Author: okccc
 * Date: 2022/11/3 2:46 下午
 * Desc: 基于xml的AOP(了解)
 */
@Component
public class LoggerAspectByXml {

    public void beforeAdvice(JoinPoint joinPoint) {
        // 获取连接点对应方法的签名信息
        Signature signature = joinPoint.getSignature();
        // 获取连接点对应方法的参数
        Object[] args = joinPoint.getArgs();
        System.out.println("Logger -> 前置通知：" + signature.getName() + "方法参数：" + Arrays.toString(args));
    }

    public void afterReturningAdvice(JoinPoint joinPoint, Object result) {
        // 获取连接点对应方法的签名信息
        Signature signature = joinPoint.getSignature();
        System.out.println("Logger -> 返回通知：" + signature.getName() + "方法返回值：" + result);
    }

    public void afterThrowingAdvice(JoinPoint joinPoint, Throwable e) {
        // 获取连接点对应方法的签名信息
        Signature signature = joinPoint.getSignature();
        System.out.println("Logger -> 异常通知：" + signature.getName() + "方法异常：" + e);
    }

    public void afterAdvice(JoinPoint joinPoint) {
        // 获取连接点对应方法的签名信息
        Signature signature = joinPoint.getSignature();
        System.out.println("Logger -> 后置通知：" + signature.getName() + "方法执行完毕");
    }

    public Object aroundAdvice(ProceedingJoinPoint joinPoint) {
        // 环绕通知方法返回值要和目标对象方法返回值一致
        Object result = null;
        try {
            System.out.println("环绕通知 -> 前置通知");
            // 被代理的目标方法执行
            result = joinPoint.proceed();
            System.out.println("环绕通知 -> 返回通知");
        } catch (Throwable e) {
            System.out.println("环绕通知 -> 异常通知");
            e.printStackTrace();
        } finally {
            System.out.println("环绕通知 -> 后置通知");
        }
        return result;
    }

}
