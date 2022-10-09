package com.okccc.mybatis.mapper;

import com.okccc.mybatis.pojo.User;
import org.apache.ibatis.annotations.Param;

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
}
