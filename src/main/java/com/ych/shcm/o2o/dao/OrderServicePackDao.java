package com.ych.shcm.o2o.dao;

import com.ych.shcm.o2o.model.OrderServicePack;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单服务dao
 *
 * @author U
 */
public interface OrderServicePackDao {

    /**
     * 根据ID查询订单服务
     *
     * @param id
     *         订单服务ID
     * @return 订单服务
     */
    OrderServicePack selectById(BigDecimal id);

    /**
     * 根据订单ID查询订单服务
     *
     * @param orderId
     *         订单ID
     * @param needItems
     *         是否需要填充orderServiceItem
     * @return 订单服务
     */
    List<OrderServicePack> selectByOrderId(BigDecimal orderId, boolean needItems);

    /**
     * 更新订单服务数据
     *
     * @param orderServicePack
     *         订单服务数据
     * @return 修改影响的行数
     */
    int update(OrderServicePack orderServicePack);

    /**
     * 插入订单服务
     *
     * @param orderServicePack
     *         订单服务数据
     * @return 插入的行数
     */
    int insert(OrderServicePack orderServicePack);

    /**
     * 插入订单服务列表
     *
     * @param orderServicePacks
     *         订单服务数据
     * @return 插入的行数
     */
    int insertList(List<OrderServicePack> orderServicePacks);

    /**
     * 删除订单服务
     *
     * @param id
     *         订单服务id
     * @return 删除的
     */
    int delete(BigDecimal id);

}
