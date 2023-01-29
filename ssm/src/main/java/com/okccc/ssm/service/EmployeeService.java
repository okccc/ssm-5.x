package com.okccc.ssm.service;

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
}
