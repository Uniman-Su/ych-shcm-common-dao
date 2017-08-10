package com.ych.shcm.o2o.dao;

import com.ych.shcm.o2o.model.PayOrder;
import com.ych.shcm.o2o.model.PayOrderOrder;
import com.ych.shcm.o2o.model.PayOrderStatusHistory;

import java.math.BigDecimal;
import java.util.List;

/**
 * 支付单Dao
 * <p>
 * Created by U on 2017/7/11.
 */
public interface PayOrderDao {

    /**
     * 插入支付单据
     *
     * @param payOrder
     *         支付单据
     * @return 插入的行数
     */
    int insert(PayOrder payOrder);

    /**
     * 更新支付单据
     *
     * @param payOrder
     *         支付单据
     * @return 修改的行数
     */
    int update(PayOrder payOrder);

    /**
     * 根据ID查找支付单据
     *
     * @param id
     *         ID
     * @return 支付单据
     */
    PayOrder selectById(BigDecimal id);

    /**
     * 根据流水号查找支付单据
     *
     * @param flowNo
     *         流水号
     * @return 支付单据
     */
    PayOrder selectByFlowNo(String flowNo);

    /**
     * 根据渠道流水号查找支付单据
     *
     * @param channelFlowNo
     *         渠道流水号
     * @return 支付单据
     */
    PayOrder selectByChannelFlowNo(String channelFlowNo);

    /**
     * 根据订单ID查找未支付的支付单
     *
     * @param orderId
     *         订单ID
     * @return 支付单据
     */
    PayOrder selectUnpayedByOrderId(BigDecimal orderId);

    /**
     * 插入变更历史
     *
     * @param statusHistory
     *         状态变更历史
     * @return 插入的行数
     */
    int insertStatusHistory(PayOrderStatusHistory statusHistory);

    /**
     * 插入支付单的订单列表
     *
     * @param orders
     *         订单列表
     * @return 插入的行数
     */
    int insertOrder(List<PayOrderOrder> orders);

    /**
     * 根据支付单ID查询订单列表
     *
     * @param payOrderId
     *         支付单ID
     * @return 单列表
     */
    List<PayOrderOrder> selectOrderByPayOrderId(BigDecimal payOrderId);

    /**
     * 根据订单ID查找支付单订单
     *
     * @param payOrderId
     *         支付单ID
     * @param orderId
     *         订单ID
     * @return 支付单订单
     */
    PayOrderOrder selectOrderByOrderId(BigDecimal payOrderId, BigDecimal orderId);

    /**
     * 根据订单ID查找支付的支付单
     *
     * @param orderId
     *         订单ID
     * @return 支付单据
     */
    PayOrder selectPayedByOrderId(BigDecimal orderId);

}
