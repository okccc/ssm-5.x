package com.okccc.spring.controller;

import com.okccc.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

/**
 * Author: okccc
 * Date: 2022/10/25 3:01 下午
 * Desc:
 */
@Controller
public class UserController {

    @Autowired
//    @Qualifier("service")
    private UserService userService;

//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }

//    @Autowired
//    public void setUserService(UserService userService) {
//        // IOC容器初始化时反射会调用无参构造创建对象,然后xml自动装配会调用setter方法给属性赋值
//        this.userService = userService;
//    }

    public void saveUser() {
        userService.saveUser();
    }
}
