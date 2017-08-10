package com.ych.shcm.o2o.dao.mybatis;

import com.ych.core.dao.BaseSqlSessionDaoSupport;
import com.ych.core.model.PagedList;
import com.ych.shcm.o2o.dao.OrderAppointmentDao;
import com.ych.shcm.o2o.model.Constants;
import com.ych.shcm.o2o.model.OrderAppointment;
import com.ych.shcm.o2o.parameter.QueryOrderAppointmentListParameter;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 订单评价数据映射
 *
 * @author U
 */
@Lazy
@Repository("shcm.o2o.dao.mybatis.OrderAppointmentDao")
public class OrderAppointmentDaoImpl extends BaseSqlSessionDaoSupport implements OrderAppointmentDao {

    private static final String NAMESPACE = "com.ych.shcm.o2o.dao.mybatis.OrderAppointmentMapper";

    private static final String INSERT = NAMESPACE + ".insert";

    private static final String UPDATE = NAMESPACE + ".update";

    private static final String SELECT_BY_ID = NAMESPACE + ".selectById";

    private static final String SELECT_ORDER_APPOINTMENT_LIST = NAMESPACE + ".selectOrderAppointmentList";

    @Resource(name = Constants.SQL_SESSION_TEMPLATE)
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.ych.b2b.common.dao.OrderDao#selectById(java.math.BigDecimal)
     */
    public OrderAppointment selectById(BigDecimal id) {
        return getSqlSession().selectOne(SELECT_BY_ID, id);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.ych.b2b.common.dao.OrderDao#update(com.ych.b2b.common.model.Order)
     */
    public int update(OrderAppointment orderAppointment) {
        return getSqlSession().update(UPDATE, orderAppointment);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.ych.b2b.common.dao.OrderDao#insert(com.ych.b2b.common.model.Order)
     */
    @Override
    public int insert(OrderAppointment orderAppointment) {
        return getSqlSession().insert(INSERT, orderAppointment);
    }

    /**
     * 查询订单预约列表
     *
     * @param parameter
     *         查询参数
     * @return 订单预约列表
     */
    @Override
    public PagedList<OrderAppointment> selectOrderAppointmentList(QueryOrderAppointmentListParameter parameter) {

        return selectPaged(SELECT_ORDER_APPOINTMENT_LIST, parameter);
    }
}
