package com.okccc.springmvc.controller;

import com.okccc.springmvc.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @Author: okccc
 * @Date: 2022/12/13 17:12
 * @Desc: SpringMVC处理Ajax请求(常用)
 *
 * Ajax(Async JavaScript and XML)：不重新加载页面的情况下,向服务器请求数据更新网页内容,实现动态加载
 * 服务器处理ajax请求后,往往需要向浏览器响应一个java对象,此时必须将java对象转换为json字符串才可以响应到浏览器
 * java类型和json格式相互转换：Pojo/Map <-> json对象、List <-> json数组
 *
 * 1.@RequestBody注解将控制器方法的形参和请求体进行绑定,获取json请求参数的步骤
 * a.pom.xml引入jackson依赖
 * b.springmvc.xml开启mvc注解驱动 <mvc:annotation-driven/>
 * c.给控制器方法的形参添加@RequestBody注解标识json请求参数将要转换成的java类型(Pojo/Map)
 *
 * 2.@ResponseBody注解将控制器方法的返回值作为响应体发送给浏览器,响应浏览器json数据的步骤
 * a.pom.xml引入jackson依赖
 * b.springmvc.xml开启mvc注解驱动 <mvc:annotation-driven/>
 * c.给控制器方法添加@ResponseBody注解,并将要转换为json字符串的java类型(Pojo/Map/List)作为控制器方法的返回值
 *
 * 3.@RestController注解相当于@Controller + @ResponseBody,这样就不用给每个控制器方法单独添加@ResponseBody
 */
@Controller
@RestController
public class AjaxController {

    @RequestMapping("/ajax/requestBody/jsonToPojo")
    public void requestJsonToPojo(@RequestBody User user, HttpServletResponse response) throws IOException {
        // 将json格式的请求参数转换成Pojo对象
        System.out.println(user);
        response.getWriter().print("json to pojo");
    }

    @RequestMapping("/ajax/requestBody/jsonToMap")
    public void requestJsonToMap(@RequestBody Map<String, Object> map, HttpServletResponse response) throws IOException {
        // 将json格式的请求参数转换成Map集合
        System.out.println(map);
        response.getWriter().print("json to map");
    }

    @RequestMapping("/ajax/responseBody")
    @ResponseBody
    public String testResponseBody() {
        return "asdsadsa";
    }

    @RequestMapping("/ajax/responseBody/pojoToJson")
    @ResponseBody
    public User responsePojoToJson() {
        // 将Pojo对象转换成json字符串响应浏览器
        return new User(1001, "grubby", "orc");
    }

    @RequestMapping("/ajax/responseBody/mapToJson")
    @ResponseBody
    public Map<String, User> responseMapToJson() {
        // 将Map集合转换成json字符串响应浏览器
        HashMap<String, User> hashMap = new HashMap<>();
        hashMap.put("1001", new User(1001, "grubby", "orc"));
        hashMap.put("1002", new User(1002, "moon", "ne"));
        hashMap.put("1003", new User(1003, "sky", "hum"));
        return hashMap;
    }

    @RequestMapping("/ajax/responseBody/listToJson")
    @ResponseBody
    public List<User> responseListToJson() {
        // 将List集合转换成json字符串响应浏览器
        ArrayList<User> list = new ArrayList<>();
        list.add(new User(1001, "grubby", "orc"));
        list.add(new User(1002, "moon", "ne"));
        list.add(new User(1003, "sky", "hum"));
        return list;
    }
}
