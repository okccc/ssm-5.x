package com.okccc.spring.process;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @Author: okccc
 * @Date: 2022/10/24 6:57 下午
 * @Desc:
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("此方法在bean的生命周期初始化之前执行");
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("此方法在bean的生命周期初始化之后执行");
        return bean;
    }
}
