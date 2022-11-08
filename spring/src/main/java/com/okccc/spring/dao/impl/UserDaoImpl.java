package com.okccc.spring.dao.impl;

import com.okccc.spring.dao.UserDao;
import org.springframework.stereotype.Repository;

/**
 * Author: okccc
 * Date: 2022/10/25 3:03 下午
 * Desc:
 */
@Repository
public class UserDaoImpl implements UserDao {
    @Override
    public void saveUser() {
        System.out.println("save user success!");
    }
}
