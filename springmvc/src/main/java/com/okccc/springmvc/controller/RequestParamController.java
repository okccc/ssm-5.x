package com.okccc.springmvc.controller;

import com.okccc.springmvc.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Author: okccc
 * Date: 2022/11/23 4:59 下午
 * Desc: SpringMVC获取请求参数的方式
 *
 * 1.通过ServletAPI获取(很少用,过于原始)
 * 往控制器方法传入HttpServletRequest对象获取请求参数
 *
 * 2.通过控制器方法的字面量类型的形参获取(常用)
 * 在控制器方法设置字面量类型的形参,形参名和请求参数名相同即可,如果不同则获取的请求参数值为null,需手动创建映射关系
 * @RequestParam、@RequestHeader、@CookieValue分别将请求参数、请求头信息、Cookie信息和控制器方法的形参进行绑定
 * value：和形参绑定的请求参数的名字
 * required：默认true,必须传输value对应的请求参数,否则页面报错400 - Required String parameter 'xxx' is not present
 * defaultValue：当没有传输value对应的请求参数时,会为形参设置一个默认值,此时和required属性无关
 *
 * 3.通过控制器方法的实体类类型的形参获取(常用)
 * 在控制器方法设置实体类类型的形参,实体类的属性名和请求参数名相同即可
 */
@Controller
public class RequestParamController {

    @RequestMapping("/param/servletApi")
    public String getParamByServletAPI(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("username=" + username + " ,password=" + password);
        return "success";
    }

    @RequestMapping("/param")
    public String getParam(String username, String password) {
        System.out.println("username=" + username + " ,password=" + password);
        return "success";
    }

    @RequestMapping("/param/requestParam")
    public String getParam(
            @RequestParam(value = "username", required = false, defaultValue = "admin") String username,
            @RequestParam(value = "password") String password,
            @RequestHeader("referer") String referer,
            @CookieValue("JSESSIONID") String jsessionid
    ) {
        System.out.println("username=" + username + " ,password=" + password);
        System.out.println("referer=" + referer + " ,jsessionid=" + jsessionid);
        return "success";
    }

    @RequestMapping("/param/pojo")
    public String getParamByPojo(User user) {
        System.out.println(user);
        return "success";
    }
}
