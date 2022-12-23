package com.okccc.springmvc.controller;

import com.okccc.springmvc.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Author: okccc
 * Date: 2022/11/23 5:02 下午
 * Desc: 控制器DispatcherServlet会统一处理浏览器请求,但不同请求处理过程也不同,所以需要处理具体请求的控制器,包含若干控制器方法
 *
 * MVC是一种软件架构思想
 * Model(模型层)：JavaBean,包括存储业务数据的User、Order等实体类以及处理业务逻辑的Service、Dao等对象
 * View(视图层)：html、jsp页面,用户交互和数据展示
 * Controller(控制层)：Servlet,处理请求和响应
 *
 * 三层架构：表述层(前台页面 + 后台servlet)、业务逻辑层、数据访问层
 * SpringMVC是Spring家族的原生产品,可以和IOC容器等基础设施无缝对接
 * SpringMVC代码简洁、可插拔式组件即插即用、性能卓越,是JavaEE项目表述层开发的首选方案
 * SpringMVC封装了Servlet,通过功能强大的前端控制器DispatcherServlet对浏览器发送的请求进行统一处理
 *
 * SpringMVC开发步骤
 * 1.创建maven工程导入依赖并将其标记为war包
 * 2.创建web.xml,配置前端控制器、编码过滤器、请求方式过滤器、监听器
 * Project Structure - Modules - springmvc - Web - Deployment Descriptors - Add web.xml - src/main/webapp/WEB-INF
 * 3.创建springmvc.xml,扫描控制层组件、配置Thymeleaf视图解析器、配置默认Servlet处理静态资源、开启mvc注解驱动
 * 4.部署到tomcat并测试：Edit Configurations - Add New Configuration - Tomcat Server - Local - Name - Deployment & Server
 */
@Controller  // 标识为控制层组件,交给SpringIOC容器管理
public class HelloController {

    @Autowired
    private HelloService helloService;

    @RequestMapping("/hello")
    public String hello() {
        // 返回逻辑视图 success -> /WEB-INF/templates/success.html
        return "success";
    }
}
