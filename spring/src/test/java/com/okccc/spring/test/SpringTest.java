package com.okccc.spring.test;

import com.alibaba.druid.pool.DruidDataSource;
import com.okccc.spring.aop.Calculator;
import com.okccc.spring.aop.CalculatorImpl;
import com.okccc.spring.aop.CalculatorStaticProxy;
import com.okccc.spring.aop.ProxyFactory;
import com.okccc.spring.controller.BookController;
import com.okccc.spring.controller.UserController;
import com.okccc.spring.dao.UserDao;
import com.okccc.spring.dao.impl.UserDaoImpl;
import com.okccc.spring.pojo.*;
import com.okccc.spring.service.UserService;
import com.okccc.spring.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2022/10/15 12:27 下午
 * @Desc:
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

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testJdbcTemplate() {
        // JdbcTemplate实现增删改查
//        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
//        JdbcTemplate jdbcTemplate = ioc.getBean(JdbcTemplate.class);

        // insert
        String sql01 = "insert into user values(null, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql01, "th000", "th000", 20, "男", "hum@qq.com");

        // update
        String sql02 = "update user set password = ? where username = ?";
        jdbcTemplate.update(sql02, "orc002", "fly");

        // delete
        String sql03 = "delete from user where username = ?";
        jdbcTemplate.update(sql03, "th000");

        // 查询单条数据返回实体类对象
        String sql04 = "select * from t_game where game_id = ?";
        Game game = jdbcTemplate.queryForObject(sql04, new BeanPropertyRowMapper<>(Game.class), 30);
        System.out.println(game);

        // 查询多条数据返回list集合
        String sql05 = "select * from t_game";
        List<Game> list = jdbcTemplate.query(sql05, new BeanPropertyRowMapper<>(Game.class));
        list.forEach(System.out::println);

        // 查询单行单列
        String sql06 = "select count(*) from t_user";
        Integer cnt = jdbcTemplate.queryForObject(sql06, Integer.class);
        System.out.println(cnt);
    }

    @Autowired
    private BookController bookController;

    @Test
    public void testTransaction() {
        /*
         *  CREATE TABLE `t_book` (
         * `book_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
         * `book_name` varchar(20) DEFAULT NULL COMMENT '图书名称',
         * `price` int(11) DEFAULT NULL COMMENT '价格',
         * `stock` int(10) unsigned DEFAULT NULL COMMENT '库存',
         * PRIMARY KEY (`book_id`)
         * ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
         *
         * insert into `t_book` values (1,'斗破苍穹',80,100),(2,'斗罗大陆',50,100);
         *
         * CREATE TABLE `t_user` (
         * `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
         * `username` varchar(20) DEFAULT NULL COMMENT '用户名',
         * `balance` int(10) unsigned DEFAULT NULL COMMENT '余额',
         * PRIMARY KEY (`user_id`)
         * ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
         *
         * insert into `t_user` values (1,'admin',50);
         *
         * 事务处理
         * 事务就是对表的更新操作,使数据从一种状态变换到另一种状态
         * 一个事务中的所有操作要么全部失败回滚(rollback),要么全部成功提交(commit)并且一旦提交就无法回滚
         * 什么时候会提交数据？
         * a.执行DML操作,默认情况下一旦执行完会自动提交数据 -> conn.setAutoCommit(false);
         * b.一旦断开数据库连接,也会提交数据 -> 将获取conn步骤从update方法中剥离出来单独关闭
         *
         * 模拟场景：用户购买图书,先查询图书价格,再更新图书库存和用户余额
         * unsigned属性会将数字类型的字段无符号化即不能为负数,比如tinyint范围(-128,127)无符号后范围(0,255)
         * user_id=1的用户购买book_id=1的图书,余额50图书80购买之后余额-30,但是balance字段设置了unsigned所以-30写不进去
         * 此时执行sql会抛异常Data truncation: BIGINT UNSIGNED value is out of range in '(`t_user`.`balance` - 80)'
         * 添加事务前：图书库存更新但用户余额没更新,这显然是错的,买书是一个完整功能,更新库存和更新余额要么都成功要么都失败
         * 添加事务后：执行sql还是抛那个异常,但是查看数据库表发现图书库存和用户余额都没更新,这才是合理的
         */

        // 测试事务属性：只读、超时、回滚策略、隔离级别
//        bookController.buyBook(1, 1);

        // 测试事务属性：传播行为
        bookController.checkout(1, new Integer[]{1, 2});
    }
}