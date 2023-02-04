package com.okccc.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: okccc
 * @Date: 2022/11/24 2:04 下午
 * @Desc: SpringMVC视图
 *
 * SpringMVC中的视图是View接口,用来渲染数据,将Model模型中的数据展示给用户
 * 转发视图和重定向视图都可以实现页面跳转,一般业务逻辑处理失败用转发,处理成功用重定向
 * 转发：地址栏url不变
 * 重定向：地址栏url会变,让浏览器重新访问重定向的地址
 *
 * 1.转发视图
 * SpringMVC默认转发视图是InternalResourceView
 * 当控制器方法中的视图名称以"forward:"为前缀时会创建InternalResourceView视图
 * 此时视图名称不会被Thymeleaf解析,而是将前缀"forward:"去掉,剩余部分作为最终路径通过转发的方式实现跳转
 * 因为无法渲染视图所以InternalResourceView已经废弃,现在转发视图都是用ThymeleafView
 *
 * 2.重定向视图
 * SpringMVC默认重定向视图是RedirectView
 * 当控制器方法中的视图名称以"redirect:"为前缀时会创建RedirectView视图
 * 此时视图名称不会被Thymeleaf解析,而是将前缀"redirect:"去掉,剩余部分作为最终路径通过重定向的方式实现跳转
 *
 * 3.视图控制器
 * 如果控制器方法仅仅用来实现页面跳转,就不用创建控制器方法,在springmvc.xml配置视图控制器view-controller即可
 * 此时只有视图控制器中的请求会被处理,其它控制器的请求映射将全部404,必须开启mvc注解驱动 <mvc:annotation-driven/>
 */
@Controller
public class ViewController {

    @RequestMapping("/view/thymeleaf")
    public String testThymeleafView() {
        return "success";
    }

    @RequestMapping("/view/forward")
    public String testInternalResourceView() {
        return "forward:/scope/model";
    }

    @RequestMapping("/view/redirect")
    public String testRedirectView() {
        return "redirect:/scope/model";
    }
}
