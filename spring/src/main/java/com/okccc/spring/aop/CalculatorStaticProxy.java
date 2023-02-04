package com.okccc.spring.aop;

/**
 * @Author: okccc
 * @Date: 2022/10/27 11:38 上午
 * @Desc: 静态代理实现了解耦但是代理对象被写死了,如果其它目标类想附加日志还得声明更多静态代理类,所以需要动态代理
 */
public class CalculatorStaticProxy implements Calculator {

    // 将被代理的目标对象声明为成员变量
    private final CalculatorImpl target;

    // 有参构造给目标对象赋值
    public CalculatorStaticProxy(CalculatorImpl target) {
        this.target = target;
    }

    @Override
    public int add(int a, int b) {
        // 附加功能由代理对象实现
        System.out.println("[INFO] add 方法开始了,参数是: " + a + "," + b);
        // 核心逻辑由目标对象实现
        int result = target.add(a, b);
        System.out.println("[INFO] add 方法结束了,结果是: " + result);
        return result;
    }

    @Override
    public int sub(int a, int b) {
        return 0;
    }

    @Override
    public int mul(int a, int b) {
        return 0;
    }

    @Override
    public int div(int a, int b) {
        return 0;
    }
}
