package com.okccc.spring.aop;

import org.springframework.stereotype.Component;

/**
 * Author: okccc
 * Date: 2022/10/27 10:54 上午
 * Desc:
 * 代码缺陷：日志作为附加功能分散在各个核心业务代码中很冗余且不好维护
 * 解决思路：解耦,将附加功能抽取出来,但是代码不连续无法抽取完整代码块,需要引入新技术
 * 代理模式：提供一个代理类,执行业务代码时不直接调用目标方法,而是通过代理类间接调用,将目标方法的非核心逻辑剥离出来
 */

@Component
public class CalculatorImpl implements Calculator {

    @Override
    public int add(int a, int b) {
//        System.out.println("[INFO] add 方法开始了,参数是: " + a + "," + b);
        int result = a + b;
        System.out.println("add方法是核心业务逻辑");
//        System.out.println("[INFO] add 方法结束了,结果是: " + result);
        return result;
    }

    @Override
    public int sub(int a, int b) {
        int result = a - b;
        System.out.println("sub方法是核心业务逻辑");
        return result;
    }

    @Override
    public int mul(int a, int b) {
        int result = a * b;
        System.out.println("mul方法是核心业务逻辑");
        return result;
    }

    @Override
    public int div(int a, int b) {
        int result = a / b;
        System.out.println("div方法是核心业务逻辑");
        return result;
    }
}
