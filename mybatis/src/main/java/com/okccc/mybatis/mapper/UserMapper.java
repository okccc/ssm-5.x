package com.okccc.mybatis.mapper;

import com.okccc.mybatis.pojo.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Author: okccc
 * Date: 2022/9/28 4:03 下午
 * Desc: mapper接口
 *
 * idea设置mybatis核心配置文件和映射文件模板
 * Intellij IDEA - Preferences - Editor - File and Code Templates - Files - mybatis-config/mapper
 *
 * mybatis参数类型
 * 1.java实体类
 * 2.单个或多个字段,通常会给参数添加@Param("key")注解,通过#{key}或者${key}获取值
 *
 * mybatis获取参数值的两种方式
 * 1.${}字符串拼接,可能存在sql注入问题
 * 2.#{}占位符赋值(推荐),但是有些特殊sql只能使用字符串拼接
 */
public interface UserMapper {

    /**
     * 添加用户并获取自增主键,注意对比插入前后User对象的id值
     */
    void insertUser(User user);

    void insertUserWithGeneratedKey(User user);

    /**
     * 修改用户
     */
    void updateUser(@Param("username") String username, @Param("password") String password);

    /**
     * 删除用户
     */
    void deleteUserById(@Param("id") Integer id);

    void deleteUserByIds(@Param("ids") String ids);

    /**
     * 根据id查询用户
     */
    User getUserById(@Param("id") Integer id);

    /**
     * 查询所有用户
     */
    List<User> getAllUser();

    /**
     * 登录校验
     */
    User checkLogin(@Param("username") String username, @Param("password") String password);

    /**
     * 统计行数
     */
    Integer getCount();

    /**
     * 将单个查询结果封装成Map集合,区别在于User类会返回所有字段而Map集合会过滤value=null的key
     */
    Map<String, Object> getUserByIdToMap(@Param("id") Integer id);

    /**
     * 将多个查询结果封装成Map集合,有两种方法
     * 1.将mapper接口中方法的返回值设置为泛型为Map的List集合(常用)
     * 2.将多个小Map放到一个大Map,由于Map放的是k-v对,所以要给小Map指定key,可以添加@MapKey注解将某个字段作为key
     */
    List<Map<String, Object>> getAllUserToMap01();

    @MapKey("id")
    Map<String, Object> getAllUserToMap02();

    /**
     * 模糊查询
     */
    List<User> getUserByLike(@Param("str") String str);

    /**
     * 动态传入表名,比如普通用户表和vip用户表字段都是一样的,就要根据传入的表名查询相应数据
     */
    List<User> selectByTableName(@Param("tableName") String tableName);
}
