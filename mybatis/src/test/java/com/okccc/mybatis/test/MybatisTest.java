package com.okccc.mybatis.test;

import com.okccc.mybatis.mapper.EmpMapper;
import com.okccc.mybatis.mapper.UserMapper;
import com.okccc.mybatis.pojo.Emp;
import com.okccc.mybatis.pojo.User;
import com.okccc.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * Author: okccc
 * Date: 2022/10/3 11:22 上午
 * Desc:
 */
public class MybatisTest {

    @Test
    public void testInsert() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        // 插入数据
        User user01 = new User(null, "grubby", "orc", 19, "男", "orc@qq.com");
        System.out.println(user01);  // User{id=null, username='grubby', ...}
        userMapper.insertUser(user01);
        System.out.println(user01);  // User{id=null, username='grubby', ...}
        // 插入数据并获取自增主键
        User user02 = new User(null, "moon", "ne", 19, "女", "ne@qq.com");
        System.out.println(user02);  // User{id=null, username='moon', ...}
        userMapper.insertUserWithGeneratedKey(user02);
        System.out.println(user02);  // User{id=23, username='moon', ...}
    }

    @Test
    public void testUpdate() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        // 修改数据
        userMapper.updateUser("moon", "123456");
    }

    @Test
    public void testDelete() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        // 删除单条
        userMapper.deleteUserById(19);
        // 删除多条
        userMapper.deleteUserByIds("8,9");
    }

    @Test
    public void testSelect() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        // 根据id查询
        User user01 = userMapper.getUserById(22);
        System.out.println(user01);
        // 查询所有
        List<User> users = userMapper.getAllUser();
        users.forEach(System.out::println);
        // 登录校验
        User user02 = userMapper.checkLogin("moon", "ne");
        System.out.println(user02);
        // 获取行数
        Integer count = userMapper.getCount();
        System.out.println(count);
        // 将查询结果User实体类转换成Map集合
        Map<String, Object> map01 = userMapper.getUserByIdToMap(21);
        System.out.println(map01);
        List<Map<String, Object>> list01 = userMapper.getAllUserToMap01();
        System.out.println(list01);
        Map<String, Object> map02 = userMapper.getAllUserToMap02();
        System.out.println(map02);
        // 模糊查询
        List<User> list02 = userMapper.getUserByLike("g");
        System.out.println(list02);
        // 动态传入表名查询
        List<User> list03 = userMapper.selectByTableName("t_user");
        System.out.println(list03);
    }

    @Test
    public void testGetEmpByEmpId() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);
        // association
        Emp emp01 = empMapper.getEmpByEmpId(3);
        System.out.println(emp01);
    }
}
