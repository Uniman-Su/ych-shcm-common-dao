package com.ych.shcm.o2o.dao;

import com.ych.core.model.PagedList;
import com.ych.shcm.o2o.model.OrderEvaluation;
import com.ych.shcm.o2o.parameter.QueryOrderAppointmentListParameter;

import java.math.BigDecimal;

/**
 * 订单评价dao
 *
 * @author U
 */
public interface OrderEvaluationDao {

    /**
     * 根据ID查询订单评价
     *
     * @param id
     *         订单评价ID
     * @return 订单评价
     */
    OrderEvaluation selectById(BigDecimal id);

    /**
     * 根据订单ID查询订单评价
     *
     * @param orderId
     *         订单ID
     * @return 订单评价
     */
    OrderEvaluation selectByOrderId(BigDecimal orderId);

    /**
     * 更新订单评价数据
     *
     * @param orderEvaluation
     *         订单评价数据
     * @return 修改影响的行数
     */
    int update(OrderEvaluation orderEvaluation);

    /**
     * 插入订单评价
     *
     * @param orderEvaluation
     *         订单评价数据
     * @return 插入的行数
     */
    int insert(OrderEvaluation orderEvaluation);

    /**
     * 查询评价列表
     *
     * @param parameter
     *         查询参数
     * @return 订单评价
     */
    PagedList<OrderEvaluation> selectOrderEvaluationList(QueryOrderAppointmentListParameter parameter);

}
