package com.ych.shcm.o2o.dao.mybatis;

import com.ych.core.dao.BaseSqlSessionDaoSupport;
import com.ych.shcm.o2o.dao.PayOrderDao;
import com.ych.shcm.o2o.model.Constants;
import com.ych.shcm.o2o.model.PayOrder;
import com.ych.shcm.o2o.model.PayOrderOrder;
import com.ych.shcm.o2o.model.PayOrderStatusHistory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * 支付单据Dao的实现
 * <p>
 * Created by U on 2017/7/11.
 */
@Lazy
@Repository("shcm.o2o.dao.mybatis.PayOrderDao")
public class PayOrderDaoImpl extends BaseSqlSessionDaoSupport implements PayOrderDao {

    private static final String NAMESPACE = "com.ych.shcm.o2o.dao.mybatis.PayOrderMapper";

    private static final String INSERT = NAMESPACE + ".insert";

    private static final String UPDATE = NAMESPACE + ".update";

    private static final String SELECT_BY_ID = NAMESPACE + ".selectById";

    private static final String SELECT_BY_FLOW_NO = NAMESPACE + ".selectByFlowNo";

    private static final String SELECT_BY_CHANNEL_FLOW_NO = NAMESPACE + ".selectByChannelFlowNo";

    private static final String SELECT_UNPAYED_BY_ORDER_ID = NAMESPACE + ".selectUnpayedByOrderId";

    private static final String INSERT_STATUS_HISTORY = NAMESPACE + ".insertStatusHistory";

    private static final String INSERT_ORDER = NAMESPACE + ".insertOrder";

    private static final String SELECT_ORDER_BY_PAY_ORDER_ID = NAMESPACE + ".selectOrderByPayOrderId";

    private static final String SELECT_ORDER_BY_ORDER_ID = NAMESPACE + ".selectOrderByOrderId";

    private static final String SELECT_PAYED_BY_ORDER_ID = NAMESPACE + ".selectPayedByOrderId";

    @Resource(name = Constants.SQL_SESSION_TEMPLATE)
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    @Override
    public int insert(PayOrder payOrder) {
        return getSqlSession().insert(INSERT, payOrder);
    }

    @Override
    public int update(PayOrder payOrder) {
        return getSqlSession().update(UPDATE, payOrder);
    }

    @Override
    public PayOrder selectById(BigDecimal id) {
        return getSqlSession().selectOne(SELECT_BY_ID, id);
    }

    @Override
    public PayOrder selectByFlowNo(String flowNo) {
        return getSqlSession().selectOne(SELECT_BY_FLOW_NO, flowNo);
    }

    @Override
    public PayOrder selectByChannelFlowNo(String channelFlowNo) {
        return getSqlSession().selectOne(SELECT_BY_CHANNEL_FLOW_NO, channelFlowNo);
    }

    @Override
    public PayOrder selectUnpayedByOrderId(BigDecimal orderId) {
        return getSqlSession().selectOne(SELECT_UNPAYED_BY_ORDER_ID, orderId);
    }

    @Override
    public int insertStatusHistory(PayOrderStatusHistory statusHistory) {
        return getSqlSession().insert(INSERT_STATUS_HISTORY, statusHistory);
    }

    @Override
    public int insertOrder(List<PayOrderOrder> orders) {
        return getSqlSession().insert(INSERT_ORDER, orders);
    }

    @Override
    public List<PayOrderOrder> selectOrderByPayOrderId(BigDecimal payOrderId) {
        return getSqlSession().selectList(SELECT_ORDER_BY_PAY_ORDER_ID, payOrderId);
    }

    @Override
    public PayOrderOrder selectOrderByOrderId(BigDecimal payOrderId, BigDecimal orderId) {
        HashMap<String, Object> parameter = new HashMap<>();
        parameter.put("payOrderId", payOrderId);
        parameter.put("orderId", orderId);
        return getSqlSession().selectOne(SELECT_ORDER_BY_ORDER_ID, parameter);
    }

    @Override
    public PayOrder selectPayedByOrderId(BigDecimal orderId) {
        return getSqlSession().selectOne(SELECT_PAYED_BY_ORDER_ID, orderId);
    }
}
