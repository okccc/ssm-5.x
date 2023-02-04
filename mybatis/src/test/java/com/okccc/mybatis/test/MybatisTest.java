package com.okccc.mybatis.test;

import com.okccc.mybatis.mapper.DeptMapper;
import com.okccc.mybatis.mapper.EmpMapper;
import com.okccc.mybatis.mapper.UserMapper;
import com.okccc.mybatis.pojo.Dept;
import com.okccc.mybatis.pojo.Emp;
import com.okccc.mybatis.pojo.User;
import com.okccc.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Author: okccc
 * @Date: 2022/10/3 11:22 上午
 * @Desc:
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
        // 分步查询
        Emp emp02 = empMapper.getEmpByEmpIdWithStepOne(3);
        System.out.println(emp02.getEmpName());
        System.out.println(emp02);
    }

    @Test
    public void testGetDeptByDeptId() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        DeptMapper deptMapper = sqlSession.getMapper(DeptMapper.class);
        // collection
        Dept dept01 = deptMapper.getDeptByDeptId(1);
        System.out.println(dept01);
        // 分步查询
        Dept dept02 = deptMapper.getDeptByDeptIdWithStepOne(1);
        System.out.println(dept02.getDeptName());
        System.out.println(dept02);
    }

    @Test
    public void testDynamicSql() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);
        // 使用if、where、trim、choose等标签动态拼接sql
        Emp emp = new Emp(null, "fly", 20, "");
        List<Emp> list = empMapper.getEmpByCondition(emp);
        list.forEach(System.out::println);
        // 批量添加
        Emp emp1 = new Emp(null, "ted1", 19, "男", null);
        Emp emp2 = new Emp(null, "ted2", 19, "男", null);
        Emp emp3 = new Emp(null, "ted3", 19, "男", null);
        empMapper.insertBatch(Arrays.asList(emp1,emp2,emp3));
        // 批量删除
        Integer[] empIds = {33, 34, 35};
        empMapper.deleteBatch(empIds);
    }

    @Test
    public void testCache() throws IOException {
        /**
         * 一级缓存是SqlSession级别(默认开启),即相同SqlSession查询的数据会被缓存
         * 一级缓存失效情况
         * 1.相同SqlSession的两次查询之间手动清空缓存
         * 2.相同SqlSession的两次查询之间有增删改操作会自动清空缓存
         *
         * 二级缓存是SqlSessionFactory级别,即相同SqlSessionFactory查询的数据会被缓存
         * 二级缓存开启条件
         * 1.在核心配置文件添加配置<setting name="cacheEnabled" value="true"/>
         * 2.在映射文件添加标签<cache/>
         * 3.查询数据默认保存在一级缓存,只有在SqlSession提交或关闭后一级缓存的数据才会保存到二级缓存
         * 4.查询数据转换成的实体类要实现序列化接口
         * 二级缓存失效情况
         * 两次查询之间有增删改操作会自动清空缓存,一级缓存和二级缓存同时失效
         * 二级缓存之整合第三方EHCache
         * pom.xml添加mybatis-ehcache依赖,映射文件添加<cache type="org.mybatis.caches.ehcache.EhcacheCache"/>
         *
         * 查询顺序：二级缓存 -> 一级缓存 -> 数据库
         * 因为一个SqlSessionFactory可以包含多个SqlSession,所以二级缓存比一级缓存范围更大
         * 查询数据时会先查二级缓存,因为二级缓存可能包含别的SqlSession已经查出来的数据,没命中再去查一级缓存,还没命中就去查数据库
         */
//        SqlSession sqlSession01 = SqlSessionUtil.getSqlSession();
//        SqlSession sqlSession02 = SqlSessionUtil.getSqlSession();
//        UserMapper mapper01 = sqlSession01.getMapper(UserMapper.class);
//        UserMapper mapper02 = sqlSession02.getMapper(UserMapper.class);
//        System.out.println(mapper01.getUserById(21));
////        User user = new User(null, "fly", "orc01", 19, "男", "orc@qq.com");
////        mapper01.insertUser(user);
////        sqlSession01.clearCache();
//        System.out.println(mapper01.getUserById(21));
//        System.out.println(mapper02.getUserById(21));

        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession01 = sqlSessionFactory.openSession(true);
        UserMapper mapper01 = sqlSession01.getMapper(UserMapper.class);
        System.out.println(mapper01.getUserById(21));
        sqlSession01.clearCache();
//        User user = new User(null, "tod", "hum", 19, "女", "hum@qq.com");
//        mapper01.insertUser(user);
        sqlSession01.close();
        SqlSession sqlSession02 = sqlSessionFactory.openSession(true);
        UserMapper mapper02 = sqlSession02.getMapper(UserMapper.class);
        System.out.println(mapper02.getUserById(21));
    }
}
