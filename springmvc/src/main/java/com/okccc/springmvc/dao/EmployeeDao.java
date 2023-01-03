package com.okccc.springmvc.dao;

import com.okccc.springmvc.pojo.Employee;

import java.util.Collection;

/**
 * Author: okccc
 * Date: 2022/11/28 3:40 下午
 * Desc:
 */
public interface EmployeeDao {

    /**
     * 查询所有员工
     */
    Collection<Employee> getAll();

    /**
     * 根据id查询员工
     */
    Employee get(Integer id);

    /**
     * 更新插入员工
     */
    void upsert(Employee employee);

    /**
     * 删除员工
     */
    void delete(Integer id);
}
