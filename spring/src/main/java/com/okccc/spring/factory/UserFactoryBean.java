package com.okccc.spring.factory;

import com.okccc.spring.pojo.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * Author: okccc
 * Date: 2022/10/25 2:33 下午
 * Desc:
 */
public class UserFactoryBean implements FactoryBean<User> {
    @Override
    public User getObject() {
        return new User();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
