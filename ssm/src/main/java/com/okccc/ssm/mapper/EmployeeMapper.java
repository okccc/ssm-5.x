package com.okccc.ssm.mapper;

import com.okccc.ssm.pojo.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2022/12/20 10:48
 * @Desc: mapper接口
 *
 * mybatis参数类型
 * 1.字面量类型,通常会给参数添加@Param("key")注解,通过#{key}或者${key}获取值
 * 2.实体类类型,通过#{}或者${}访问实体类的属性名就可以获取相对应的属性值
 *
 * mybatis获取参数值的两种方式
 * 1.${}字符串拼接,可能存在sql注入问题
 * 2.#{}占位符赋值(推荐),但是有些特殊sql只能使用字符串拼接
 */
public interface EmployeeMapper {

    /**
     * 查询所有员工
     */
    List<Employee> getAllEmployee();

    /**
     * 添加员工
     */
    void insertEmployee(Employee employee);

    /**
     * 根据empId查询员工
     */
    Employee getEmployeeByEmpId(@Param("empId") Integer empId);

    /**
     * 修改员工
     */
    void updateEmployee(Employee employee);

    /**
     * 根据empId删除员工
     */
    void deleteEmployeeByEmpId(@Param("empId") Integer empId);

    /**
     * 统计员工数量
     */
    int countEmployee();
}