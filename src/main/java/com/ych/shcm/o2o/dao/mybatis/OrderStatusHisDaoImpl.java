package com.ych.shcm.o2o.dao.mybatis;

import com.ych.core.dao.BaseSqlSessionDaoSupport;
import com.ych.shcm.o2o.dao.OrderStatusHisDao;
import com.ych.shcm.o2o.model.Constants;
import com.ych.shcm.o2o.model.OrderStatus;
import com.ych.shcm.o2o.model.OrderStatusHis;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mxp on 2017/7/13.
 */
@Lazy
@Repository("shcm.o2o.dao.mybatis.OrderStatusHisDao")
public class OrderStatusHisDaoImpl extends BaseSqlSessionDaoSupport implements OrderStatusHisDao {

    private static final String NAMESPACE = "com.ych.shcm.o2o.dao.mybatis.OrderStatusHisMapper";

    private static final String INSERT = NAMESPACE + ".insert";

    private static final String UPDATE = NAMESPACE + ".update";

    private static final String SELECT_BY_ID = NAMESPACE + ".selectById";

    private static final String SELECT_BY_ORDER_ID = NAMESPACE + ".selectByOrderId";

    private static final String SELECT_LATEST = NAMESPACE + ".selectLatest";

    @Resource(name = Constants.SQL_SESSION_TEMPLATE)
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    /**
     * 根据ID查询订单状态历史记录
     *
     * @param id
     *         订单状态历史记录ID
     * @return 订单状态历史记录
     */
    @Override
    public OrderStatusHis selectById(BigDecimal id) {
        return getSqlSession().selectOne(SELECT_BY_ID, id);
    }

    /**
     * 根据订单ID查询订单状态历史记录
     *
     * @param orderId
     *         订单ID
     * @return 订单状态历史记录
     */
    @Override
    public List<OrderStatusHis> selectByOrderId(BigDecimal orderId) {
        return getSqlSession().selectList(SELECT_BY_ORDER_ID, orderId);

    }

    /**
     * 更新订单状态历史记录数据
     *
     * @param orderStatusHis
     *         订单状态历史记录数据
     * @return 修改影响的行数
     */
    @Override
    public int update(OrderStatusHis orderStatusHis) {
        return getSqlSession().update(UPDATE, orderStatusHis);
    }

    /**
     * 插入订单状态历史记录
     *
     * @param orderStatusHis
     *         订单状态历史记录数据
     * @return 插入的行数
     */
    @Override
    public int insert(OrderStatusHis orderStatusHis) {
        return getSqlSession().update(INSERT, orderStatusHis);
    }

    @Override
    public OrderStatusHis selectLatest(BigDecimal orderId, OrderStatus orderStatus) {
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("orderId", orderId);
        parameter.put("status", orderStatus);
        return getSqlSession().selectOne(SELECT_LATEST, parameter);
    }
}
