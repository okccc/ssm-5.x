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
}
