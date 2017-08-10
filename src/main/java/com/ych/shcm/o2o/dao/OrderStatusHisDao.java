package com.ych.shcm.o2o.dao;

import com.ych.shcm.o2o.model.OrderStatus;
import com.ych.shcm.o2o.model.OrderStatusHis;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单状态历史记录dao
 *
 * @author U
 */
public interface OrderStatusHisDao {

    /**
     * 根据ID查询订单状态历史记录
     *
     * @param id
     *         订单状态历史记录ID
     * @return 订单状态历史记录
     */
    OrderStatusHis selectById(BigDecimal id);

    /**
     * 根据订单ID查询订单状态历史记录
     *
     * @param orderId
     *         订单ID
     * @return 订单状态历史记录
     */
    List<OrderStatusHis> selectByOrderId(BigDecimal orderId);

    /**
     * 更新订单状态历史记录数据
     *
     * @param orderStatusHis
     *         订单状态历史记录数据
     * @return 修改影响的行数
     */
    int update(OrderStatusHis orderStatusHis);

    /**
     * 插入订单状态历史记录
     *
     * @param orderStatusHis
     *         订单状态历史记录数据
     * @return 插入的行数
     */
    int insert(OrderStatusHis orderStatusHis);

    /**
     * 查找订单状态最近变更的历史
     *
     * @param orderId
     *         订单ID
     * @param orderStatus
     *         订单状态
     * @return 订单状态最近变更的历史
     */
    OrderStatusHis selectLatest(BigDecimal orderId, OrderStatus orderStatus);

}
