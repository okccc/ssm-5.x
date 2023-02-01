package com.okccc.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    @Override
    public PageInfo<Employee> getEmployeeByPage(Integer pageNum) {
        // 查询之前开启分页功能
        PageHelper.startPage(pageNum, 3);
        // 查询数据获取集合
        List<Employee> employeeList = employeeMapper.getAllEmployee();
        // 查询之后获取分页相关数据
        PageInfo<Employee> pageInfo = new PageInfo<>(employeeList);
        System.out.println(pageInfo);
        /*
         * 分页相关数据
         * PageInfo{
         *     pageNum=1, pageSize=3, size=3, startRow=1, endRow=3, total=14, pages=5,
         *     list=Page{count=true, pageNum=1, pageSize=3, startRow=0, endRow=3, total=14, pages=5, reasonable=false, pageSizeZero=false}
         *     [Employee{id=1, empId=1001, empName='grubby', age=18, gender='0', email='orc@qq.com'}, Employee{id=2, ...}],
         *     prePage=0, nextPage=2, isFirstPage=true, isLastPage=false, hasPreviousPage=false, hasNextPage=true,
         *     navigatePages=8, navigateFirstPage=1, navigateLastPage=5, navigatepageNums=[1, 2, 3, 4, 5]
         * }
         *
         * pageNum：当前页码
         * pageSize：每页显示条数
         * size：当前页显示的真实条数
         * total：总记录数
         * pages：总页数
         * prePage：上一页的页码
         * nextPage：下一页的页码
         * isFirstPage/isLastPage：是否为第一页/最后一页
         * hasPreviousPage/hasNextPage：是否存在上一页/下一页
         * navigatePages：导航分页的页码数
         * navigatepageNums：导航分页的页码 [1,2,3,4,5]
         */
        return pageInfo;
    }

    @Override
    public void insertEmployee(Employee employee) {
        // 添加员工
        employeeMapper.insertEmployee(employee);
    }

    @Override
    public Employee getEmployeeByEmpId(Integer empId) {
        // 根据empId查询员工
        return employeeMapper.getEmployeeByEmpId(empId);
    }

    @Override
    public void updateEmployee(Employee employee) {
        // 修改员工
        employeeMapper.updateEmployee(employee);
    }
}
