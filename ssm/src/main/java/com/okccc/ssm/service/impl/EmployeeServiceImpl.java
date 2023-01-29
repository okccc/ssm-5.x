package com.okccc.ssm.service.impl;

import com.okccc.ssm.mapper.EmployeeMapper;
import com.okccc.ssm.pojo.Employee;
import com.okccc.ssm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2022/12/19 14:46
 * @Desc:
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<Employee> getAllEmployee() {
        // 查询所有员工
        return employeeMapper.getAllEmployee();
    }
}
