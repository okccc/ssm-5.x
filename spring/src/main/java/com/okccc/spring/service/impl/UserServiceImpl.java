package com.okccc.spring.service.impl;

import com.okccc.spring.dao.UserDao;
import com.okccc.spring.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Author: okccc
 * Date: 2022/10/25 3:02 下午
 * Desc:
 */
@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void saveUser() {
        userDao.saveUser();
    }
}
