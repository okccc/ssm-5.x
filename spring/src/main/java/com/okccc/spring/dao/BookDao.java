package com.okccc.spring.dao;

/**
 * @Author: okccc
 * @Date: 2022/11/7 4:55 下午
 * @Desc:
 */
public interface BookDao {

    /**
     * 根据id查询图书价格
     */
    Integer getPriceByBookId(Integer bookId);

    /**
     * 更新图书库存
     */
    void updateStock(Integer bookId);

    /**
     * 更新用户余额
     */
    void updateBalance(Integer userId, Integer price);
}
