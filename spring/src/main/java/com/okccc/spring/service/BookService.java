package com.okccc.spring.service;

/**
 * Author: okccc
 * Date: 2022/11/7 6:03 下午
 * Desc:
 */
public interface BookService {

    /**
     * 买书
     */
    void buyBook(Integer userId, Integer bookId);
}
