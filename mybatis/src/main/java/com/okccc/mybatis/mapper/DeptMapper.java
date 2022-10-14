package com.okccc.mybatis.mapper;

import com.okccc.mybatis.pojo.Dept;
import org.apache.ibatis.annotations.Param;

/**
 * Author: okccc
 * Date: 2022/10/7 10:01 上午
 * Desc:
 */
public interface DeptMapper {

    /**
     * 根据deptId查询部门以及对应员工
     */
    Dept getDeptByDeptId(@Param("deptId") Integer deptId);

    /**
     * 分步查询部门以及对应员工的第一步
     */
    Dept getDeptByDeptIdWithStepOne(@Param("deptId") Integer deptId);

    /**
     * 分步查询员工以及对应部门的第二步
     */
    Dept getEmpByEmpIdWithStepTwo(@Param("deptId") Integer deptId);
}
