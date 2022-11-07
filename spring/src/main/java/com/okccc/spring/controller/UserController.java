package com.okccc.spring.controller;

import com.okccc.spring.service.UserService;

/**
 * Author: okccc
 * Date: 2022/10/25 3:01 下午
 * Desc:
 */
public class UserController {

    private UserService userService;

    // IOC容器初始化时反射会调用无参构造创建对象,然后xml自动装配会调用setter方法给属性赋值
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void saveUser() {
        userService.saveUser();
    }
}
