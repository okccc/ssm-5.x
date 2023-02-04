package com.okccc.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: okccc
 * @Date: 2022/11/9 3:18 下午
 * @Desc: @RequestMapping注解
 * 
 * 请求映射：将浏览器发送的请求路径映射到当前控制器方法执行,标识在类上设置初始信息,标识在方法上设置具体信息
 * 1.value属性：通过请求路径匹配(常用)
 * value属性是String[]类型,浏览器发送的请求路径匹配数组中任意一个值,该请求就会被当前控制器方法执行
 * 如果请求路径不匹配会报404 - NOT FOUND
 *
 * 2.method属性：通过请求方式匹配(常用)
 * method属性是RequestMethod[]类型,浏览器发送的请求方式匹配数组中任意一个值,该请求就会被当前控制器方法执行
 * 如果请求路径匹配但请求方式不匹配会报405 - Request method 'xxx' not supported
 * SpringMVC提供了@RequestMapping的派生注解：@GetMapping、@PostMapping、@PutMapping、@DeleteMapping
 *
 * 3.params属性：通过请求参数匹配,有四种表达式(很少用)
 * "username"：当前匹配请求的请求参数必须携带username
 * "!username"：当前匹配请求的请求参数不能携带username
 * "key=value"：当前匹配请求的请求参数必须携带key且key=value
 * "key!=value"：当前匹配请求的请求参数可以不携带key,如果携带key那么key!=value
 * 如果请求路径匹配但请求参数不匹配会报400 - Parameter conditions "username" not met for actual request parameters
 *
 * 4.headers属性：通过请求头信息匹配,有四种表达式(很少用)
 * "username"：当前匹配请求的请求头必须携带username
 * "!username"：当前匹配请求的请求头不能携带username
 * "key=value"：当前匹配请求的请求头必须携带key且key=value
 * "key!=value"：当前匹配请求的请求头可以不携带key,如果携带key那么key!=value
 * 如果请求路径匹配但请求头不匹配会报404,比如headers={"referer"}那必须是跳转过来的页面,首次访问就会404
 *
 * 5.SpringMVC支持ant风格的路径,value属性可以设置一些特殊字符
 * ?：任意单个字符(不包括"?"和"/",因为地址栏里的"?"是请求路径和请求参数的分隔符,"/"是请求路径之间的分隔符)
 * *：任意个数的任意字符(不包括"?"和"/",原因同上)
 * **：任意层数的任意目录,必须写成**在两个"/"中间,前后不能有任何其他字符否则会被当成字符*
 *
 * 6.SpringMVC支持路径中的占位符(常用)
 * 传统方式/deleteUser?id=1传递的是键值对,Rest风格/user/delete/1传递的是值
 * value属性使用占位符{xxx}表示路径中的数据,再通过@PathVariable注解将占位符和控制器方法的形参绑定
 */
@Controller
//@RequestMapping("/hello")
public class RequestMappingController {

    @RequestMapping(
            value = {"/", "/index", "/aaa"},
            method = {RequestMethod.GET, RequestMethod.POST}
//            params = {"username", "!password", "age=20", "gender!='女'"}
//            headers = {"referer"}
    )
    public String index() {
        return "index";
    }

    @RequestMapping("/detail")
    public String detail() {
        return "success";
    }

    @RequestMapping("/**/s?s/ant")
    public String testAnt() {
        return "success";
    }

    @RequestMapping("/test/rest/{username}/{id}")
    public String testRest(@PathVariable("username") String username, @PathVariable("id") Integer id) {
        System.out.println("username=" + username + " ,id=" + id);
        return "success";
    }
}
