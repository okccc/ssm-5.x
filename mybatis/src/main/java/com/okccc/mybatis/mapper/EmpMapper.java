package com.okccc.mybatis.mapper;

import com.okccc.mybatis.pojo.Emp;
import org.apache.ibatis.annotations.Param;

/**
 * Author: okccc
 * Date: 2022/10/7 10:01 上午
 * Desc:
 */
public interface EmpMapper {

    /**
     * 根据empId查询员工以及对应部门
     */
    Emp getEmpByEmpId(@Param("empId") Integer empId);
}
