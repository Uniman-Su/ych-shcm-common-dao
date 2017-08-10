package com.ych.shcm.o2o.dao.mybatis;

import com.ych.core.dao.BaseSqlSessionDaoSupport;
import com.ych.shcm.o2o.dao.OrderBillDao;
import com.ych.shcm.o2o.model.Constants;
import com.ych.shcm.o2o.model.OrderBill;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * Created by mxp on 2017/7/13.
 */
@Lazy
@Repository("shcm.o2o.dao.mybatis.OrderBillDao")
public class OrderBillDaoImpl extends BaseSqlSessionDaoSupport implements OrderBillDao {

    private static final String NAMESPACE = "com.ych.shcm.o2o.dao.mybatis.OrderBillMapper";

    private static final String INSERT = NAMESPACE + ".insert";

    private static final String UPDATE = NAMESPACE + ".update";

    private static final String DELETE = NAMESPACE + ".delete";

    private static final String SELECT_BY_ID = NAMESPACE + ".selectById";

    private static final String SELECT_BY_ORDER_ID = NAMESPACE + ".selectByOrderId";

    @Resource(name = Constants.SQL_SESSION_TEMPLATE)
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    /**
     * 根据ID查询订单发票
     *
     * @param id
     *         订单发票ID
     * @return 订单发票
     */
    @Override
    public OrderBill selectById(BigDecimal id) {
        return getSqlSession().selectOne(SELECT_BY_ID, id);
    }

    /**
     * 根据订单ID查询订单发票
     *
     * @param orderId
     *         订单ID
     * @return 订单发票
     */
    @Override
    public OrderBill selectByOrderId(BigDecimal orderId) {
        return getSqlSession().selectOne(SELECT_BY_ORDER_ID, orderId);
    }

    /**
     * 更新订单发票数据
     *
     * @param orderBill
     *         订单发票数据
     * @return 修改影响的行数
     */
    @Override
    public int update(OrderBill orderBill) {
        return getSqlSession().update(UPDATE, orderBill);
    }

    /**
     * 插入订单发票
     *
     * @param orderBill
     *         订单发票数据
     * @return 插入的行数
     */
    @Override
    public int insert(OrderBill orderBill) {
        return getSqlSession().insert(INSERT, orderBill);
    }

    /**
     * 删除订单发票
     *
     * @param id
     *         订单发票id
     * @return 删除的
     */
    @Override
    public int delete(BigDecimal id) {
        return getSqlSession().delete(DELETE, id);
    }
}
