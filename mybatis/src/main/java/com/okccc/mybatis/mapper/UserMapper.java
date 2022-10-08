package com.okccc.mybatis.mapper;

import com.okccc.mybatis.pojo.User;

/**
 * Author: okccc
 * Date: 2022/9/28 4:03 下午
 * Desc: mapper接口
 * idea设置mybatis核心配置文件和映射文件模板
 * Intellij IDEA - Preferences - Editor - File and Code Templates - Files - mybatis-config/mybatis-mapper
 */
public interface UserMapper {

    /**
     * 添加用户并获取自增主键,注意对比插入前后User对象的id值
     */
    void insertUser(User user);

    void insertUserWithGeneratedKey(User user);
}
