package com.okccc.spring.service.impl;

import com.okccc.spring.dao.BookDao;
import com.okccc.spring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
 * @Author: okccc
 * @Date: 2022/11/7 6:04 下午
 * @Desc: 事务属性之只读、超时、回滚策略、隔离级别、传播行为
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    @Transactional(
            // 只读：表示当前操作不涉及写操作,这样数据库就能针对查询操作进行优化
            // Connection is read-only. Queries leading to data modification are not allowed
//            readOnly = true,

            // 超时：因为某些问题导致程序卡住会长时间占用数据库连接,应该超时回滚及时释放资源,好让别的程序运行
            // Transaction timed out: deadline was Tue Nov 08 18:23:36 CST 2022
            timeout = 3,

            // 回滚策略：声明式事务默认只对运行时异常回滚,编译时异常不回滚,可以手动配置
            // 买书功能虽然出现数学运算异常ArithmeticException,但是更新库存和更新余额正常执行
            noRollbackFor = ArithmeticException.class,
            noRollbackForClassName = "java.lang.ArithmeticException",

            // 隔离级别：和ACID的持久性有关,读未提交、读已提交(Oracle默认)、可重复度(MySQL默认,且不会出现幻读)、串行化
            isolation = Isolation.DEFAULT,

            // 传播行为：当一个事务方法调用另一个事务方法时,必须指定事务如何传播,是在现有事务中继续运行(默认)还是开启新的事务运行
//            propagation = Propagation.REQUIRED  // 当前线程有已经开启的事务可用,那么就在这个事务中继续运行
            propagation = Propagation.REQUIRES_NEW  // 不管当前线程是否有已经开启的事务,都会另外开启新的事务运行
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
        // 验证事务回滚策略
//        System.out.println(1/0);
    }

}
