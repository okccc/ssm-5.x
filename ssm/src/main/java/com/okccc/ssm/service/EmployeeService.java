package com.okccc.ssm.service;

import com.github.pagehelper.PageInfo;
import com.okccc.ssm.pojo.Employee;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2022/12/19 14:47
 * @Desc:
 */
public interface EmployeeService {

    /**
     * 查询所有员工
     */
    List<Employee> getAllEmployee();

    /**
     * 查询员工分页
     */
    PageInfo<Employee> getEmployeeByPage(Integer pageNum);

    /**
     * 添加员工
     */
    void insertEmployee(Employee employee);
}