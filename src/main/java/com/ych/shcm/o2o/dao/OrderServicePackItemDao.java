package com.ych.shcm.o2o.dao;

import com.ych.shcm.o2o.model.OrderServicePackItem;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单服务包项目dao
 *
 * @author U
 */
public interface OrderServicePackItemDao {

    /**
     * 根据ID查询订单服务包项目
     *
     * @param id
     *         订单服务包项目ID
     * @return 订单服务包项目
     */
    OrderServicePackItem selectById(BigDecimal id);

    /**
     * 根据订单ID查询订单服务包项目
     *
     * @param OrderServicePackId
     *         订单服务包ID
     * @return 订单服务包项目
     */
    List<OrderServicePackItem> selectByOrderServicePackId(BigDecimal OrderServicePackId);

    /**
     * 更新订单服务包项目数据
     *
     * @param orderServicePackItem
     *         订单服务包项目数据
     * @return 修改影响的行数
     */
    int update(OrderServicePackItem orderServicePackItem);

    /**
     * 插入订单服务包项目
     *
     * @param orderServicePackItem
     *         订单服务包项目数据
     * @return 插入的行数
     */
    int insert(OrderServicePackItem orderServicePackItem);

    /**
     * 插入订单服务包项目列表
     *
     * @param orderServicePackItems
     *         订单服务包项目数据列表
     * @return 插入的行数
     */
    int insertList(List<OrderServicePackItem> orderServicePackItems);

    /**
     * 删除订单服务包项目
     *
     * @param id
     *         订单服务包项目id
     * @return 删除的
     */
    int delete(BigDecimal id);


}
