package com.okccc.spring.pojo;

import java.util.Arrays;
import java.util.Map;

/**
 * Author: okccc
 * Date: 2022/10/17 4:40 下午
 * Desc:
 */
public class Emp {

    private Integer empId;

    private String empName;

    private Integer age;

    private String sex;

    private Dept dept;

    private String[] hobbies;

    private Map<String, Game> games;

    public Emp() {
    }

    public Emp(Integer id, String name, Integer age, String sex) {
        this.empId = id;
        this.empName = name;
        this.age = age;
        this.sex = sex;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public String[] getHobbies() {
        return hobbies;
    }

    public void setHobbies(String[] hobbies) {
        this.hobbies = hobbies;
    }

    public Map<String, Game> getGames() {
        return games;
    }

    public void setGames(Map<String, Game> games) {
        this.games = games;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", dept=" + dept +
                ", hobbies=" + Arrays.toString(hobbies) +
                ", games=" + games +
                '}';
    }
}
