package com.okccc.springmvc.controller;

import com.okccc.springmvc.dao.EmployeeDao;
import com.okccc.springmvc.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;

/**
 * Author: okccc
 * Date: 2022/11/28 4:09 下午
 * Desc: 使用Restful风格结合ModelAndView实现CRUD
 *
 * 查询所有员工 -> /employee  GET
 * 跳转到添加页面 -> /to/add  GET
 * 添加员工 -> /employee  POST
 * 跳转到修改页面 -> /employee/1  GET
 * 修改员工 -> /employee  PUT
 * 删除员工 -> /employee/1  DELETE
 */
@Controller
public class EmployeeController {

    // 这里为了方便演示直接使用Dao操作Map集合,SSM整合后持久层是Mybatis,业务层引入Mapper接口操作数据库
    @Autowired
    private EmployeeDao employeeDao;

    /**
     * 查询所有员工
     */
    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public String getAllEmployee(Model model) {
        // 获取所有员工信息
        Collection<Employee> employeeList = employeeDao.getAll();
        // 将数据共享到请求域
        model.addAttribute("employeeList", employeeList);
        // 跳转到列表页面
        return "employee_list";
    }

    /**
     * 跳转到添加页面
     */
    @RequestMapping(value = "/to/add", method = RequestMethod.GET)
    public String toAdd() {
        // 跳转到添加页面,只用来实现页面跳转可以使用视图控制器
        return "employee_add";
    }

    /**
     * 添加员工
     */
    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    public String addEmployee(Employee employee) {
        // 添加员工信息
        employeeDao.upsert(employee);
        // 重定向到列表页
        return "redirect:/employee";
    }

    /**
     * 跳转到修改页面
     */
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public String toUpdate(@PathVariable("id") Integer id, Model model) {
        // 根据id查询员工
        Employee employee = employeeDao.get(id);
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
        employeeDao.upsert(employee);
        // 重定向到列表页
        return "redirect:/employee";
    }

    /**
     * 删除员工
     */
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
    public String deleteEmployee(@PathVariable("id") Integer id) {
        // 删除员工信息
        employeeDao.delete(id);
        // 重定向到列表页
        return "redirect:/employee";
    }
}
