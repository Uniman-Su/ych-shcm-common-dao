package com.ych.shcm.o2o.dao;

import com.ych.shcm.o2o.model.RefundOrder;

/**
 * 退款单Dao
 */
public interface RefundOrderDao {

    /**
     * 插入退款单
     * @param refundOrder 退款单
     * @return 插入的行数
     */
    int insert(RefundOrder refundOrder);

    /**
     * 更新退款单
     * @param refundOrder 退款单
     * @return 更新的行数
     */
    int udpate(RefundOrder refundOrder);



}
