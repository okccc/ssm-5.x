package com.okccc.spring.test;

import com.alibaba.druid.pool.DruidDataSource;
import com.okccc.spring.pojo.*;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Author: okccc
 * Date: 2022/10/15 12:27 下午
 * Desc:
 */
public class SpringTest {

    @Test
    public void testIOC() throws Exception {
        // 获取IOC容器,工程最终会打包到服务器运行,而服务器磁盘路径不一定有当前文件,所以选ClassPathXml而不是FileSystemXml
        // java包和resources包最终都会加载到classes路径下,所以代码可以直接访问配置文件
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");

        // 1.根据id获取bean
        Demo bean01 = (Demo) ioc.getBean("demo");
        System.out.println(bean01);
        // 2.根据类型获取bean(常用)
        // 要求IOC容器中指定类型的bean有且只能有一个,因为默认是单例模式,IOC容器初始化时就会创建该对象
        // 在满足bean唯一性的前提下,其实是看(对象 instanceof 类型)返回的结果,bean类型、bean继承的类型、bean实现的接口都可以
        // 如果没有类型匹配的bean,抛异常：NoSuchBeanDefinitionException
        // 如果有多个类型匹配的bean,抛异常：NoUniqueBeanDefinitionException
        Demo bean02 = ioc.getBean(Demo.class);
        Person bean03 = ioc.getBean(Person.class);
        System.out.println(bean02);
        System.out.println(bean03);
        System.out.println(bean02 instanceof Person ? "0" : "1");
        // 3.根据id和类型获取bean
        Demo bean04 = ioc.getBean("demo", Demo.class);
        System.out.println(bean04);

        Class<?> clazz = Class.forName("com.okccc.spring.pojo.Demo");
        Object o = clazz.newInstance();
        System.out.println(o);
    }

    @Test
    public void testDI() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 依赖注入之setter注入
        Emp emp01 = ioc.getBean("emp01", Emp.class);
        System.out.println(emp01);  // Student{id=1, name='grubby', age=19, sex='男'}

        // 依赖注入之构造器注入
        Emp emp02 = ioc.getBean("emp02", Emp.class);
        System.out.println(emp02);  // Student{id=2, name='moon', age=20, sex='女'}

        Emp emp03 = ioc.getBean("emp03", Emp.class);
        System.out.println(emp03);

        Dept dept01 = ioc.getBean("dept01", Dept.class);
        System.out.println(dept01);
    }

    @Test
    public void testJDBC() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        DruidDataSource dataSource = ioc.getBean(DruidDataSource.class);
        System.out.println(dataSource.getConnectCount());
    }

    @Test
    public void testScope() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        User bean01 = ioc.getBean("user", User.class);
        User bean02 = ioc.getBean("user", User.class);
        System.out.println(bean01 == bean02);
    }

    @Test
    public void testLifeCycle() {
        // ConfigurableApplicationContext子接口提供了容器的刷新和关闭功能
        ConfigurableApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        User bean = ioc.getBean("user", User.class);
        System.out.println(bean);
        ioc.close();
    }
}