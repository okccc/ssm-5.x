package com.okccc.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.okccc.ssm.pojo.Employee;
import com.okccc.ssm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

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
 *
 * 报错：ERROR com.alibaba.druid.pool.DruidDataSource - testWhileIdle is true, validationQuery not set
 * 原因：没有校验druid数据源的连接是否有效
 * 解决：spring.xml配置druid数据源时添加相关校验属性
 *
 * 使用RestFul风格结合ModelAndView实现员工的CRUD
 * 查询所有员工    ->  /employee         GET
 * 查询员工分页    ->  /employee/page/1  GET
 * 跳转到添加页面  ->  /to/add           GET
 * 添加员工       ->  /employee         POST
 * 根据id查询员工  ->  /employee/1       GET
 * 修改员工       ->  /employee         PUT
 * 根据id删除员工  ->  /employee/1       DELETE
 */
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 查询所有员工
     */
    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public String getAllEmployee(Model model) {
        // 查询所有员工信息
        List<Employee> employeeList = employeeService.getAllEmployee();
        // 将数据共享到请求域
        model.addAttribute("employeeList", employeeList);
        // 跳转到列表页面
        return "employee_list";
    }

    /**
     * 查询员工分页
     */
    @RequestMapping(value = "/employee/page/{pageNum}", method = RequestMethod.GET)
    public String getEmployeeByPage(@PathVariable("pageNum") Integer pageNum, Model model) {
        // 查询员工分页信息
        PageInfo<Employee> pageInfo = employeeService.getEmployeeByPage(pageNum);
        // 将数据共享到请求域
        model.addAttribute("pageInfo", pageInfo);
        // 跳转到列表页面
        return "employee_page";
    }

    /**
     * 跳转到添加页面
     */
    @RequestMapping(value = "/to/add", method = RequestMethod.GET)
    public String toAdd() {
        // 跳转到添加页面,只用来实现页面跳转时可以使用视图控制器
        return "employee_add";
    }

    /**
     * 添加员工
     */
    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    public String addEmployee(Employee employee) {
        // 添加员工信息
        employeeService.insertEmployee(employee);
        // 重定向到列表页
        return "redirect:/employee";
    }

    /**
     * 根据id查询员工,跳转到修改页面
     */
    @RequestMapping(value = "/employee/{empId}", method = RequestMethod.GET)
    public String toUpdate(@PathVariable("empId") Integer empId, Model model) {
        // 根据id查询员工
        Employee employee = employeeService.getEmployeeByEmpId(empId);
        // 将数据共享到请求域
        model.addAttribute("employee", employee);
        // 跳转到修改页面
        return "employee_update";
    }

    /**
     * 修改员工
     */
    @RequestMapping(value = "/employee", method = RequestMethod.PUT)
    public String updateEmployee(Employee employee) {
        // 更新员工信息
        employeeService.updateEmployee(employee);
        // 重定向到列表页
        return "redirect:/employee";
    }

    /**
     * 删除员工
     */
    @RequestMapping(value = "/employee/{empId}", method = RequestMethod.DELETE)
    public String deleteEmployeeByEmpId(@PathVariable("empId") Integer empId) {
        // 删除员工信息
        employeeService.deleteEmployeeByEmpId(empId);
        // 重定向到列表页
        return "redirect:/employee";
    }

    /**
     * 统计员工数量
     */
    @RequestMapping(value = "/employee/count", method = RequestMethod.GET)
    public String countEmployee(Model model) {
        // 统计员工数量
        int count = employeeService.countEmployee();
        // 将数据共享到请求域
        model.addAttribute("count", count);
        // 跳转到统计页面
        return "employee_count";
    }
}