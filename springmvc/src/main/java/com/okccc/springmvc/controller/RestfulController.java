package com.okccc.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Author: okccc
 * Date: 2022/11/25 6:44 下午
 * Desc: RestFul风格
 *
 * 1.REST(Representational State Transfer)：表述层资源状态转移
 * 资源：服务器上一切皆资源,使用URI标识,URI既是资源名称也是资源在web上的地址,客户端通过URI与资源进行交互
 * 资源的表述：描述资源在某个时刻的State,比如HTML、XML、JSON、文本、图片、音频、视频
 * 状态转移：在客户端和服务器之间Transfer资源
 *
 * 2.Restful的实现
 * HTTP协议表示操作方式的4个动词 GET、POST、PUT、DELETE
 * Rest风格提倡将请求参数作为URL地址的一部分,各个单词使用斜杠分开,而不是?key=value的方式
 * 操作类型    传统方式             Rest风格
 * 查询操作    getUserById?id=1    user/1 -> GET
 * 保存操作    saveUser            user -> POST
 * 更新操作    updateUser          user -> PUT
 * 删除操作    deleteUser?id=1     user/1 -> DELETE
 *
 * 3.HiddenHttpMethodFilter
 * 浏览器只支持GET和POST请求,SpringMVC提供了请求方法过滤器将POST请求转换为PUT或DELETE
 * 实现方式：先设置form表单请求方式method="post",再添加name="_method" value="put/delete"指定最终请求方式
 */
@Controller
public class RestfulController {

    @GetMapping("/user")
//    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String getAllUser() {
        System.out.println("查询所有用户");
        return "success";
    }

    @GetMapping("/user/{id}")
//    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public String getUserById(@PathVariable("id") Integer id) {
        System.out.println("根据id查询用户：" + id);
        return "success";
    }

    @PostMapping("/user")
//    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String addUser() {
        System.out.println("添加用户");
        return "success";
    }

    @PutMapping("/user")
//    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public String updateUser() {
        System.out.println("修改用户");
        return "success";
    }

    @DeleteMapping(value = "/user/{id}")
//    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable("id") Integer id) {
        System.out.println("根据id删除用户：" + id);
        return "success";
    }
}
