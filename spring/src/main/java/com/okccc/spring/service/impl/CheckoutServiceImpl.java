package com.okccc.spring.service.impl;

import com.okccc.spring.service.BookService;
import com.okccc.spring.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Author: okccc
 * Date: 2022/11/7 6:25 下午
 * Desc: 事务属性之传播行为
 */
@Service
public class CheckoutServiceImpl implements CheckoutService {

    @Autowired
    private BookService bookService;

    @Override
    @Transactional
    public void checkout(Integer userId, Integer[] bookIds) {
        /*
         * 当前线程已有checkout事务,checkout方法会调用buyBook方法,用户余额100两本图书分别是80和50
         * 设置buyBook()事务属性：@Transactional(propagation = Propagation.REQUIRED)
         * buyBook会在checkout事务中运行,因此在买第二本书时余额不足导致整个checkout事务回滚,一本也买不了
         * 设置buyBook()事务属性：@Transactional(propagation = Propagation.REQUIRES_NEW)
         * buyBook每次都会开启新的事务运行,因此第一本书购买成功事务结束,第二本书购买失败事务回滚,互不影响,能买几本就买几本
         */
        for (Integer bookId : bookIds) {
            bookService.buyBook(userId, bookId);
        }
    }
}
