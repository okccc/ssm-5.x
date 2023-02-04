package com.okccc.spring.service;

/**
 * @Author: okccc
 * @Date: 2022/11/7 6:24 下午
 * @Desc:
 */
public interface CheckoutService {

    /**
     * 结账
     */
    void checkout(Integer userId, Integer[] bookIds);
}
