package com.ych.shcm.o2o.dao.mybatis;

import com.ych.core.dao.BaseSqlSessionDaoSupport;
import com.ych.core.model.PagedList;
import com.ych.shcm.o2o.dao.OrderDao;
import com.ych.shcm.o2o.model.Constants;
import com.ych.shcm.o2o.model.Order;
import com.ych.shcm.o2o.model.OrderStatusCount;
import com.ych.shcm.o2o.parameter.QueryOrderListParameter;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * 订单数据映射
 *
 * @author U
 */
@Lazy
@Repository("shcm.o2o.dao.mybatis.OrderDao")
public class OrderDaoImpl extends BaseSqlSessionDaoSupport implements OrderDao {

    private static final String NAMESPACE = "com.ych.shcm.o2o.dao.mybatis.OrderMapper";

    private static final String INSERT = NAMESPACE + ".insert";

    private static final String UPDATE = NAMESPACE + ".update";

    private static final String SELECT_BY_ID = NAMESPACE + ".selectById";

    private static final String SELECT_BY_NO = NAMESPACE + ".selectByNo";

    private static final String SELECT_LIST = NAMESPACE + ".selectOrderList";

    private static final String SELECT_ORDER_COUNT = NAMESPACE + ".selectOrderCountGroupByType";

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
    public Order selectById(BigDecimal id) {
        return getSqlSession().selectOne(SELECT_BY_ID, id);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.ych.b2b.common.dao.OrderDao#selectByNo(java.lang.String)
     */
    public Order selectByNo(String orderNo) {
        return getSqlSession().selectOne(SELECT_BY_NO, orderNo);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.ych.b2b.common.dao.OrderDao#update(com.ych.b2b.common.model.Order)
     */
    public int update(Order order) {
        return getSqlSession().update(UPDATE, order);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.ych.b2b.common.dao.OrderDao#insert(com.ych.b2b.common.model.Order)
     */
    @Override
    public int insert(Order order) {
        return getSqlSession().insert(INSERT, order);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.ych.b2b.common.dao.OrderDao#selectOrderList(com.ych.b2b.common.model.
     * parameter.QueryOrderListParameter)
     */
    @Override
    public PagedList<Order> selectOrderList(QueryOrderListParameter parameter) {

        return selectPaged(SELECT_LIST, parameter);
    }

    /**
     * 分组查询订单数量 用户ID可为空
     *
     * @param userId
     *         用户id
     * @return 数量列表（根据OrderStatus依次排列）
     */
    @Override
    public List<OrderStatusCount> selectOrderCountGroupByType(BigDecimal userId) {
        HashMap hashMap = new HashMap();
        hashMap.put("userId", userId);
        return getSqlSession().selectList(SELECT_ORDER_COUNT, hashMap);

    }

}
