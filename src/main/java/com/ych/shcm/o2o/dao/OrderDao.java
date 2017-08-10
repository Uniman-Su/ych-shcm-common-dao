package com.ych.shcm.o2o.dao;

import com.ych.core.model.PagedList;
import com.ych.shcm.o2o.model.Order;
import com.ych.shcm.o2o.model.OrderStatusCount;
import com.ych.shcm.o2o.parameter.QueryOrderListParameter;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单数据访问接口
 *
 * @author U
 */
public interface OrderDao {

    /**
     * 根据ID查询订单
     *
     * @param id
     *         订单ID
     * @return 订单
     */
    Order selectById(BigDecimal id);

    /**
     * 根据订单号查询订单
     *
     * @param orderNo
     *         订单号
     * @return 订单
     */
    Order selectByNo(String orderNo);

    /**
     * 更新订单数据
     *
     * @param order
     *         订单
     * @return 修改影响的行数
     */
    int update(Order order);

    /**
     * 插入订单
     *
     * @param order
     *         订单
     * @return 插入的行数
     */
    int insert(Order order);

    /**
     * 查询订单列表
     *
     * @param parameter
     *         查询参数
     * @return 查询结果
     */
    PagedList<Order> selectOrderList(QueryOrderListParameter parameter);

    /**
     * 分组查询订单数量 用户ID可为空
     *
     * @param userId
     *         用户id
     * @return 数量列表（根据OrderStatus依次排列）
     */
    List<OrderStatusCount> selectOrderCountGroupByType(BigDecimal userId);

}
