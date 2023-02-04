package com.okccc.mybatis.mapper;

import com.okccc.mybatis.pojo.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2022/10/7 10:01 上午
 * @Desc:
 */
public interface EmpMapper {

    /**
     * 根据empId查询员工以及对应部门
     */
    Emp getEmpByEmpId(@Param("empId") Integer empId);

    /**
     * 分步查询员工以及对应部门的第一步
     */
    Emp getEmpByEmpIdWithStepOne(@Param("empId") Integer empId);

    /**
     * 分步查询部门以及对应员工的第二步
     */
    List<Emp> getDeptByDeptIdWithStepTwo(@Param("deptId") Integer deptId);

    /**
     * 根据条件查询员工信息
     */
    List<Emp> getEmpByCondition(Emp emp);

    /**
     * 批量添加
     */
    void insertBatch(@Param("emps") List<Emp> emps);

    /**
     * 批量删除
     */
    void deleteBatch(@Param("empIds") Integer[] empIds);
//    void deleteBatch(@Param("empIds") List<Integer> empIds);
}
