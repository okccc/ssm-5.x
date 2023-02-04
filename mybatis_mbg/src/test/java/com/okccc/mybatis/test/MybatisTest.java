package com.okccc.mybatis.test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.okccc.mybatis.mapper.EmpMapper;
import com.okccc.mybatis.pojo.Emp;
import com.okccc.mybatis.pojo.EmpExample;
import com.okccc.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2022/10/14 2:48 下午
 * @Desc:
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

    @Test
    public void testPage() {
        /**
         * 分页插件使用步骤：
         * 1.pom.xml添加依赖
         * 2.在mybatis核心配置文件配置<plugins>
         *
         * 分页相关数据
         * PageInfo{
         * pageNum=1, pageSize=5, size=5, startRow=1, endRow=5, total=23, pages=5,
         * list=Page{count=true, pageNum=1, pageSize=5, startRow=0, endRow=5, total=23, pages=5, reasonable=false, pageSizeZero=false}
         * [Emp{empId=1, empName='grubby', age=19, gender='男', deptId=1},Emp{empId=2, empName='moon', age=20, gender='女', deptId=2}...],
         * prePage=0, nextPage=2, isFirstPage=true, isLastPage=false, hasPreviousPage=false, hasNextPage=true,
         * navigatePages=8, navigateFirstPage=1, navigateLastPage=5, navigatepageNums=[1, 2, 3, 4, 5]}
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
         * navigatepageNums：导航分页的页码，[1,2,3,4,5]
         */
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        // 查询之前开启分页功能
        PageHelper.startPage(1, 5);
        // 查询数据获取集合
        List<Emp> list = mapper.selectByExample(null);
        list.forEach(System.out::println);
        // 查询之后获取分页相关数据
        PageInfo<Emp> pageInfo = new PageInfo<>(list);
        System.out.println(pageInfo);
    }
}
