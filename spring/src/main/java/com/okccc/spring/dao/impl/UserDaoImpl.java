package com.okccc.spring.dao.impl;

import com.okccc.spring.dao.UserDao;

/**
 * Author: okccc
 * Date: 2022/10/25 3:03 下午
 * Desc:
 */
public class UserDaoImpl implements UserDao {
    @Override
    public void saveUser() {
        System.out.println("save user success!");
    }
}
