package com.ych.shcm.o2o.dao;

import com.ych.core.model.PagedList;
import com.ych.shcm.o2o.model.OrderAppointment;
import com.ych.shcm.o2o.parameter.QueryOrderAppointmentListParameter;

import java.math.BigDecimal;

/**
 * 订单预约dao
 *
 * @author U
 */
public interface OrderAppointmentDao {

    /**
     * 根据ID查询订单预约
     *
     * @param id
     *         订单预约ID
     * @return 订单预约
     */
    OrderAppointment selectById(BigDecimal id);

    /**
     * 更新订单预约数据
     *
     * @param orderAppointment
     *         订单预约数据
     * @return 修改影响的行数
     */
    int update(OrderAppointment orderAppointment);

    /**
     * 插入订单
     *
     * @param orderAppointment
     *         订单预约数据
     * @return 插入的行数
     */
    int insert(OrderAppointment orderAppointment);

    /**
     * 查询订单预约列表
     *
     * @param parameter
     *         查询参数
     * @return 订单预约列表
     */
    PagedList<OrderAppointment> selectOrderAppointmentList(QueryOrderAppointmentListParameter parameter);
}
