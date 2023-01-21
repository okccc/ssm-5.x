package com.okccc.ssm.controller;

/**
 * @Author: okccc
 * @Date: 2022/12/19 14:46
 * @Desc: SSM整合步骤
 *
 * 1.创建maven工程导入相关依赖并标记为war包
 * 2.创建web.xml：Spring监听器、Spring编码过滤器、SpringMVC请求方式过滤器、SpringMVC前端控制器
 * 3.创建springmvc.xml：扫描控制层组件、Thymeleaf视图解析器、默认Servlet、mvc注解驱动、视图控制器、文件上传解析器
 * 4.部署到tomcat并启动：Edit Configurations - Add New Configuration - Tomcat Server - Local - Name - Deployment & Server
 * 5.搭建mybatis环境：jdbc.properties、mybatis-config.xml、Mapper接口和映射文件、log4j.xml
 * 6.创建spring.xml：扫描其它层组件、mysql数据源以及事务、spring整合mybatis
 *
 * 报错：java: Compilation failed: internal java compiler error
 * 原因：项目的jdk版本不对
 * 解决：Intellij IDEA - Preferences - Build - Compiler - Java Compiler - Module - Target bytecode version - 8
 *
 * 报错：spring.xml上方提示module ssm. File is included in 4 contexts
 * 解决：Project Structure - Modules - ssm - Spring - 先删除右侧所有xml再重新添加进来
 */