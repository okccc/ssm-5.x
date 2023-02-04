package com.okccc.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Author: okccc
 * @Date: 2022/11/3 2:46 下午
 * @Desc: 基于注解的AOP(推荐)
 */
@Component  // 表示这是一个普通类,是实现IOC功能的注解
@Aspect  // 表示这是一个切面类,是实现AOP功能的注解
public class LoggerAspectByAnn {
    /*
     * AOP相关术语
     * 横切关注点：从核心业务代码中抽出来的非核心业务代码,比如日志功能
     * 通知：实现横切关注点的方法,包括前置通知、返回通知、异常通知、后置通知、环绕通知
     * 切面：封装通知方法的类
     * 连接点：抽取横切关注点的位置
     * 切入点：从代码层面定位连接点,即通过切入点表达式再套回到目标方法上
     *
     * 1.通知方法：在切面中通过指定注解标识
     * @Before：前置通知,在被代理的目标方法前执行
     * @AfterReturning/@AfterThrowing：返回通知/异常通知,在被代理的目标方法成功结束/异常结束后执行
     * @After：后置通知,在被代理的目标方法最终结束后执行
     * @Around：环绕通知,try..catch..finally围绕整个被代理的目标方法,包括上面4种通知对应的所有位置,不会同时使用
     *
     * 2.切入点表达式：标识通知方法的注解的value属性
     * execution(public int com.okccc.spring.aop.CalculatorImpl.add(int, int))
     * execution(* com.okccc.spring.aop.CalculatorImpl.*(..))
     * 第一个*表示任意的权限修饰符和返回值类型
     * 第二个*表示任意方法,get*是以get开头的方法,包名.类名也可以用*.*表示,*Service是以Service结尾的类或接口
     * ..表示任意参数列表
     *
     * 3.获取通知相关信息
     * 获取连接点信息：在通知方法的参数位置设置JoinPoint类型参数
     * 获取目标方法返回值：@AfterReturning注解的returning属性,将通知方法的某个形参接收目标方法的返回值
     * 获取目标方法异常：@AfterThrowing注解的throwing属性,将通知方法的某个形参接收目标方法的异常
     *
     * 4.切面优先级：@Order注解的value属性值越小优先级越高
     */

    // 为了方便重用可以设置公共的切入点表达式
    @Pointcut("execution(* com.okccc.spring.aop.*.*(..))")
    public void pointCut(){}

    //    @Before("execution(public int com.okccc.spring.aop.annotation.CalculatorImpl.add(int, int))")
//    @Before("execution(* com.okccc.spring.aop.annotation.CalculatorImpl.*(..))")
    @Before("pointCut()")
    public void beforeAdvice(JoinPoint joinPoint) {
        // 获取连接点对应方法的签名信息
        Signature signature = joinPoint.getSignature();
        // 获取连接点对应方法的参数
        Object[] args = joinPoint.getArgs();
        System.out.println("Logger -> 前置通知：" + signature.getName() + "方法参数：" + Arrays.toString(args));
    }

    @AfterReturning(value = "pointCut()", returning = "result")
    public void afterReturningAdvice(JoinPoint joinPoint, Object result) {
        // 获取连接点对应方法的签名信息
        Signature signature = joinPoint.getSignature();
        System.out.println("Logger -> 返回通知：" + signature.getName() + "方法返回值：" + result);
    }

    @AfterThrowing(value = "pointCut()", throwing = "e")
    public void afterThrowingAdvice(JoinPoint joinPoint, Throwable e) {
        // 获取连接点对应方法的签名信息
        Signature signature = joinPoint.getSignature();
        System.out.println("Logger -> 异常通知：" + signature.getName() + "方法异常：" + e);
    }

    @After("pointCut()")
    public void afterAdvice(JoinPoint joinPoint) {
        // 获取连接点对应方法的签名信息
        Signature signature = joinPoint.getSignature();
        System.out.println("Logger -> 后置通知：" + signature.getName() + "方法执行完毕");
    }

    @Around("pointCut()")
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
