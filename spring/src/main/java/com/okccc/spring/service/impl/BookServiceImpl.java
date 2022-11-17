package com.okccc.spring.service.impl;

import com.okccc.spring.dao.BookDao;
import com.okccc.spring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
 * Author: okccc
 * Date: 2022/11/7 6:04 下午
 * Desc: 事务属性之只读、超时、回滚策略、隔离级别、传播行为
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    @Transactional(
            // 只读：表示当前操作不涉及写操作,这样数据库就能针对查询操作进行优化
            // Connection is read-only. Queries leading to data modification are not allowed
            readOnly = true
    )
    public void buyBook(Integer userId, Integer bookId) {
        try {
            // 睡眠5秒验证事务超时
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 查询图书价格
        Integer price = bookDao.getPriceByBookId(bookId);
        // 更新图书库存
        bookDao.updateStock(bookId);
        // 更新用户余额
        bookDao.updateBalance(userId, price);
    }

}
