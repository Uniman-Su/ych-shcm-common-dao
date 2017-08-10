package com.ych.shcm.o2o.dao;


import com.ych.shcm.o2o.model.OrderBill;

import java.math.BigDecimal;

/**
 * 订单发票dao
 *
 * @author U
 */
public interface OrderBillDao {

    /**
     * 根据ID查询订单发票
     *
     * @param id
     *         订单发票ID
     * @return 订单发票
     */
    OrderBill selectById(BigDecimal id);

    /**
     * 根据订单ID查询订单发票
     *
     * @param orderId
     *         订单ID
     * @return 订单发票
     */
    OrderBill selectByOrderId(BigDecimal orderId);


    /**
     * 更新订单发票数据
     *
     * @param orderBill
     *         订单发票数据
     * @return 修改影响的行数
     */
    int update(OrderBill orderBill);

    /**
     * 插入订单发票
     *
     * @param orderBill
     *         订单发票数据
     * @return 插入的行数
     */
    int insert(OrderBill orderBill);


    /**
     * 删除订单发票
     * @param id 订单发票id
     * @return 删除的
     */
    int delete(BigDecimal id);

}
