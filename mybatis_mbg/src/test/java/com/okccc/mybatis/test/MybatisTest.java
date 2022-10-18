package com.okccc.mybatis.test;

import com.okccc.mybatis.mapper.EmpMapper;
import com.okccc.mybatis.pojo.Emp;
import com.okccc.mybatis.pojo.EmpExample;
import com.okccc.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * Author: okccc
 * Date: 2022/10/14 2:48 下午
 * Desc:
 */
public class MybatisTest {

    @Test
    public void testMBG() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        // 根据id查询数据
        Emp emp = mapper.selectByPrimaryKey(1);
        System.out.println(emp);

        // 不写条件就是查询所有数据
        List<Emp> list = mapper.selectByExample(null);
        list.forEach(System.out::println);

        // 根据条件查询数据
        EmpExample example = new EmpExample();
        example.createCriteria().andEmpNameEqualTo("grubby").andDeptIdEqualTo(1);
        example.or().andAgeBetween(21, 25);
        // select * from t_emp WHERE ( emp_name = ? and dept_id = ? ) or( age between ? and ? )
        List<Emp> list1 = mapper.selectByExample(example);
        list1.forEach(System.out::println);

        // 普通修改：赋值为null表示将该字段值修改为null
        Emp emp1 = new Emp(30, "grubby", null, "女");
        // update t_emp set emp_name = ?, age = ?, gender = ?, dept_id = ? where emp_id = ?
        mapper.updateByPrimaryKey(emp1);

        // 选择性修改：赋值为null表示不修改该字段值
        Emp emp2 = new Emp(31, "grubby", null, "女");
        // update t_emp SET emp_name = ?, gender = ? where emp_id = ?
        mapper.updateByPrimaryKeySelective(emp2);
    }
}
