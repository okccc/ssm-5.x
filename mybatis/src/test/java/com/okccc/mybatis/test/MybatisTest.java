package com.okccc.mybatis.test;

import com.okccc.mybatis.mapper.UserMapper;
import com.okccc.mybatis.pojo.User;
import com.okccc.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * Author: okccc
 * Date: 2022/10/3 11:22 上午
 * Desc:
 */
public class MybatisTest {

    @Test
    public void testInsertUser() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User(null, "moon", "ne", 20, "女", "ne@qq.com");
        System.out.println(user);  // User{id=null, username='moon', ...}
        userMapper.insertUser(user);
        System.out.println(user);  // User{id=null, username='moon', ...}
        sqlSession.close();
    }

    @Test
    public void testInsertUserWithGeneratedKey() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User(null, "moon", "ne", 20, "女", "ne@qq.com");
        System.out.println(user);  // User{id=null, username='moon', ...}
        userMapper.insertUserWithGeneratedKey(user);
        System.out.println(user);  // User{id=11, username='moon', ...}
    }
}