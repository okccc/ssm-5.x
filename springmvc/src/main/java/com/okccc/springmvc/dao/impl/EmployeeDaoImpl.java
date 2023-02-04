package com.okccc.springmvc.dao.impl;

import com.okccc.springmvc.dao.EmployeeDao;
import com.okccc.springmvc.pojo.Employee;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: okccc
 * @Date: 2022/11/28 3:39 下午
 * @Desc: 使用Map集合简单模拟EmployeeDao,真实场景应该是JdbcTemplate操作数据库
 */
@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    private static final Map<Integer, Employee> employees;

    static {
        // 先弄一些初始数据
        employees = new HashMap<>();
        employees.put(1001, new Employee(1001, "aaa", "aaa@qq.com", 1));
        employees.put(1002, new Employee(1002, "bbb", "bbb@qq.com", 0));
        employees.put(1003, new Employee(1003, "ccc", "ccc@qq.com", 0));
        employees.put(1004, new Employee(1004, "ddd", "ddd@qq.com", 1));
    }

    private Integer initId = 1005;

    @Override
    public Collection<Employee> getAll() {
        return employees.values();
    }

    @Override
    public Employee get(Integer id) {
        return employees.get(id);
    }

    @Override
    public void upsert(Employee employee) {
        if (employee.getId() == null) {
            employee.setId(initId++);
        }
        // 有就更新,没有就新增
        employees.put(employee.getId(), employee);
    }

    @Override
    public void delete(Integer id) {
        employees.remove(id);
    }
}
