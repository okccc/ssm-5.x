package com.okccc.spring.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @Author: okccc
 * @Date: 2022/10/27 1:59 下午
 * @Desc: 生产代理对象的工厂类,可以动态生成任意目标类的代理类
 * 动态代理有两种
 * 1.jdk动态代理(常用)：要求必须有接口,最终生成的代理类和目标类实现相同的接口,在com.sun.proxy包下
 * 2.cglib动态代理：最终生成的代理类会继承目标类,并且和目标类在相同包下
 */
public class ProxyFactory {

    // 将被代理的目标对象声明为成员变量,因为目标类型不确定所以用Object接收
    private final Object target;

    // 有参构造给目标对象赋值
    public ProxyFactory(Object target) {
        this.target = target;
    }

    // 生产代理对象
    public Object getProxy() {
        // 获取加载动态生成的代理类的类加载器,因为类要执行必须先通过类加载器加载,类加载器是通过反射获取
        ClassLoader classLoader = target.getClass().getClassLoader();
        // 获取目标类实现的所有接口,因为代理类要和目标类实现同样的接口
        Class<?>[] interfaces = target.getClass().getInterfaces();
        // 代理类实现接口要重写其抽象方法
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // proxy是代理对象,method是要执行的方法,args是要执行方法的参数列表
                Object result = null;
                // 代理类的方法如何重写？调用目标对象实现功能
                try {
                    System.out.println("[INFO] " + method.getName() + " 方法开始了,参数是：" + Arrays.toString(args));
                    result = method.invoke(target, args);
                    System.out.println("[INFO] " + method.getName() + " 方法结束了,结果是：" + result);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("[INFO] " + method.getName() + " 方法异常：" + e);
                } finally {
                    System.out.println("[INFO] " + method.getName() + " 方法执行完毕");
                }
                return result;
            }
        };
        // 返回目标对象的代理对象
        return Proxy.newProxyInstance(classLoader, interfaces, handler);
    }
}
