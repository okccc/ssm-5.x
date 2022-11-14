package com.okccc.spring.test;

import com.alibaba.druid.pool.DruidDataSource;
import com.okccc.spring.aop.Calculator;
import com.okccc.spring.aop.CalculatorImpl;
import com.okccc.spring.aop.CalculatorStaticProxy;
import com.okccc.spring.aop.ProxyFactory;
import com.okccc.spring.controller.UserController;
import com.okccc.spring.dao.UserDao;
import com.okccc.spring.dao.impl.UserDaoImpl;
import com.okccc.spring.pojo.*;
import com.okccc.spring.service.UserService;
import com.okccc.spring.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Author: okccc
 * Date: 2022/10/15 12:27 下午
 * Desc:
 */
// Spring整合Junit4,表示当前类是在spring测试环境执行,这样就可以通过注入方式直接获取IOC容器的bean
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SpringTest {

    @Test
    public void testIOC() throws Exception {
        // 加载配置文件(IOC容器初始化)
        // java包和resources包最终都会加载到classes路径下,所以代码可以直接访问配置文件
        // 工程最终会打包到服务器运行,而服务器磁盘路径不一定有当前文件,所以选ClassPath而不是FileSystem
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
        // bean的作用域
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        User bean01 = ioc.getBean("user", User.class);
        User bean02 = ioc.getBean("user", User.class);
        System.out.println(bean01 == bean02);
    }

    @Test
    public void testLifeCycle() {
        // bean的生命周期：ConfigurableApplicationContext子接口提供了容器的刷新和关闭功能
        ConfigurableApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        User bean = ioc.getBean("user", User.class);
        System.out.println(bean);
        ioc.close();
    }

    @Test
    public void testFactoryBean() {
        // FactoryBean
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        User bean = ioc.getBean("userFactoryBean", User.class);
        System.out.println(bean);
    }

    @Test
    public void testAutowireByXml() {
        // 基于xml的自动装配
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserController userController = ioc.getBean(UserController.class);
        userController.saveUser();
    }

    @Test
    public void testAutowireByAnnotation() {
        // 基于注解管理bean
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserController userController = ioc.getBean(UserController.class);
        System.out.println(userController);
        UserService userService = ioc.getBean("userService", UserServiceImpl.class);
        System.out.println(userService);
        UserDao userDao = ioc.getBean(UserDaoImpl.class);
        System.out.println(userDao);
        // 基于注解的自动装配
        userController.saveUser();
    }

    @Test
    public void testProxy() {
        // 不使用代理：目标对象直接实现
        CalculatorImpl calculatorImpl = new CalculatorImpl();
        System.out.println(calculatorImpl.add(10, 20));

        // 使用静态代理：通过代理对象实现
        CalculatorStaticProxy staticProxy = new CalculatorStaticProxy(new CalculatorImpl());
        System.out.println(staticProxy.add(10, 20));

        // 使用动态代理：虽然还不知道代理对象是啥,但是代理对象和目标对象实现了相同接口,所以返回接口类型即可
        ProxyFactory proxyFactory = new ProxyFactory(new CalculatorImpl());
        // java.lang.ClassCastException: com.sun.proxy.$Proxy4 cannot be cast to com.okccc.spring.proxy.CalculatorImpl
//        CalculatorImpl proxy = (CalculatorImpl) proxyFactory.getProxy();
        Calculator dynamicProxy = (Calculator) proxyFactory.getProxy();
        System.out.println(dynamicProxy.add(10, 20));
//        System.out.println(dynamicProxy.div(10, 0));
    }

    @Test
    public void testAOP() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        // AOP底层是动态代理,此时IOC容器无法获取目标对象只能获取代理对象,不然代理模式就没意义了
        // NoSuchBeanDefinitionException: No qualifying bean of type 'com.okccc.spring.proxy.CalculatorImpl' available
//        CalculatorImpl calculator = ioc.getBean(CalculatorImpl.class);
        Calculator calculator = ioc.getBean(Calculator.class);
        System.out.println(calculator.div(10, 5));
    }
}