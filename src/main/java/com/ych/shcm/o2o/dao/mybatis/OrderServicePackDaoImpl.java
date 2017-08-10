package com.ych.shcm.o2o.dao.mybatis;

import com.ych.core.dao.BaseSqlSessionDaoSupport;
import com.ych.shcm.o2o.dao.OrderServicePackDao;
import com.ych.shcm.o2o.model.Constants;
import com.ych.shcm.o2o.model.OrderServicePack;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mxp on 2017/7/13.
 */
@Lazy
@Repository("shcm.o2o.dao.mybatis.OrderServicePackDao")
public class OrderServicePackDaoImpl extends BaseSqlSessionDaoSupport implements OrderServicePackDao {

    private static final String NAMESPACE = "com.ych.shcm.o2o.dao.mybatis.OrderServicePackMapper";

    private static final String INSERT = NAMESPACE + ".insert";

    private static final String INSERT_LIST = NAMESPACE + ".insertList";

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
     * 根据ID查询订单服务
     *
     * @param id
     *         订单服务ID
     * @return 订单服务
     */
    @Override
    public OrderServicePack selectById(BigDecimal id) {
        return getSqlSession().selectOne(SELECT_BY_ID, id);
    }

    /**
     * 根据订单ID查询订单服务
     *
     * @param orderId
     *         订单ID
     * @param needItems
     *         是否需要填充orderServiceItem
     * @return 订单服务
     */
    @Override
    public List<OrderServicePack> selectByOrderId(BigDecimal orderId, boolean needItems) {
        HashMap map = new HashMap();
        map.put("orderId", orderId);
        map.put("needItems", needItems);
        return getSqlSession().selectList(SELECT_BY_ORDER_ID, map);
    }

    /**
     * 更新订单服务数据
     *
     * @param orderServicePack
     *         订单服务数据
     * @return 修改影响的行数
     */
    @Override
    public int update(OrderServicePack orderServicePack) {
        return getSqlSession().update(UPDATE, orderServicePack);
    }

    /**
     * 插入订单服务
     *
     * @param orderServicePack
     *         订单服务数据
     * @return 插入的行数
     */
    @Override
    public int insert(OrderServicePack orderServicePack) {
        return getSqlSession().insert(INSERT, orderServicePack);
    }

    /**
     * 插入订单服务列表
     *
     * @param orderServicePacks
     *         订单服务数据
     * @return 插入的行数
     */
    @Override
    public int insertList(List<OrderServicePack> orderServicePacks) {
        return getSqlSession().insert(INSERT_LIST, orderServicePacks);
    }

    /**
     * 删除订单服务
     *
     * @param id
     *         订单服务id
     * @return 删除的
     */
    @Override
    public int delete(BigDecimal id) {
        return getSqlSession().delete(DELETE, id);
    }

}
