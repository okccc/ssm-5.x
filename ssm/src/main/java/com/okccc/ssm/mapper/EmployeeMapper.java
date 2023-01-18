package com.okccc.ssm.mapper;

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
}