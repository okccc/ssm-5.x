package com.okccc.springmvc.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @Author: okccc
 * @Date: 2022/12/15 16:13
 * @Desc: 异常处理器(了解)：1.基于配置的异常处理 2.基于注解的异常处理
 */
@ControllerAdvice  // 将当前类标识为异常处理的组件
public class ExceptionController {

    @ExceptionHandler(ArithmeticException.class)
    public String handleException(Exception e, Model model) {
        // 向请求域共享数据
        model.addAttribute("e", e);
        return "error";
    }
}
