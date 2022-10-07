package com.okccc.mybatis.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Author: okccc
 * Date: 2022/9/28 5:05 下午
 * Desc: SqlSession工具类
 */
public class SqlSessionUtil {

    public static SqlSession getSqlSession() {
        SqlSession sqlSession = null;
        try {
            // 读取mybatis核心配置文件
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            // 创建SqlSessionFactory对象,是生产SqlSession的工厂
            // 工厂模式：如果创建某个对象的过程基本固定,就可以将其封装成工厂类,通过工厂类生产需要的对象
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            // 创建SqlSession对象,SqlSession是java程序和数据库之间的会话,HttpSession是java程序和浏览器之间的会话
            sqlSession = sqlSessionFactory.openSession(true);
//            // 使用代理模式创建mapper接口的代理实现类对象
//            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
//            // 执行crud操作,会根据mapper接口的全类名去映射文件匹配方法名对应的sql语句并执行
//            List<User> list = userMapper.getAllUser();
//            System.out.println(list);
//            // 关闭SqlSession
//            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sqlSession;
    }
}
