package com.ych.shcm.o2o.dao.mybatis;

import com.ych.core.dao.BaseSqlSessionDaoSupport;
import com.ych.core.model.PagedList;
import com.ych.shcm.o2o.dao.OrderEvaluationDao;
import com.ych.shcm.o2o.model.Constants;
import com.ych.shcm.o2o.model.OrderEvaluation;
import com.ych.shcm.o2o.parameter.QueryOrderAppointmentListParameter;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 订单评价数据
 * Created by mxp on 2017/7/12.
 */
@Lazy
@Repository("shcm.o2o.dao.mybatis.OrderEvaluationDao")
public class OrderEvaluationDaoImpl extends BaseSqlSessionDaoSupport implements OrderEvaluationDao {

    private static final String NAMESPACE = "com.ych.shcm.o2o.dao.mybatis.OrderEvaluationMapper";

    private static final String INSERT = NAMESPACE + ".insert";

    private static final String UPDATE = NAMESPACE + ".update";

    private static final String SELECT_BY_ID = NAMESPACE + ".selectById";

    private static final String SELECT_BY_ORDER_ID = NAMESPACE + ".selectByOrderId";

    private static final String SELECT_ORDER_EVALUATION_LIST = NAMESPACE + ".selectOrderEvaluationList";

    @Resource(name = Constants.SQL_SESSION_TEMPLATE)
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    /**
     * 根据ID查询订单评价
     *
     * @param id
     *         订单评价ID
     * @return 订单评价
     */
    @Override
    public OrderEvaluation selectById(BigDecimal id) {
        return getSqlSession().selectOne(SELECT_BY_ID, id);

    }

    /**
     * 根据订单ID查询订单评价
     *
     * @param orderId
     *         订单ID
     * @return 订单评价
     */
    @Override
    public OrderEvaluation selectByOrderId(BigDecimal orderId) {
        return getSqlSession().selectOne(SELECT_BY_ORDER_ID, orderId);

    }

    /**
     * 更新订单评价数据
     *
     * @param orderEvaluation
     *         订单评价数据
     * @return 修改影响的行数
     */
    @Override
    public int update(OrderEvaluation orderEvaluation) {
        return getSqlSession().update(UPDATE, orderEvaluation);

    }

    /**
     * 插入订单评价
     *
     * @param orderEvaluation
     *         订单评价数据
     * @return 插入的行数
     */
    @Override
    public int insert(OrderEvaluation orderEvaluation) {
        return getSqlSession().insert(INSERT, orderEvaluation);

    }

    /**
     * 查询评价列表
     *
     * @param parameter
     *         查询参数
     * @return 订单评价
     */
    @Override
    public PagedList<OrderEvaluation> selectOrderEvaluationList(QueryOrderAppointmentListParameter parameter) {
        return selectPaged(SELECT_ORDER_EVALUATION_LIST, parameter);
    }
}
