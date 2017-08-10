package com.ych.shcm.o2o.dao.mybatis;

import com.ych.core.dao.BaseSqlSessionDaoSupport;
import com.ych.shcm.o2o.dao.OrderServicePackItemDao;
import com.ych.shcm.o2o.model.Constants;
import com.ych.shcm.o2o.model.OrderServicePackItem;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by mxp on 2017/7/13.
 */
@Lazy
@Repository("shcm.o2o.dao.mybatis.OrderServicePackItemDao")
public class OrderServicePackItemDaoImpl extends BaseSqlSessionDaoSupport implements OrderServicePackItemDao {

    private static final String NAMESPACE = "com.ych.shcm.o2o.dao.mybatis.OrderServicePackItemMapper";

    private static final String INSERT = NAMESPACE + ".insert";

    private static final String INSERT_LIST = NAMESPACE + ".insertList";

    private static final String UPDATE = NAMESPACE + ".update";

    private static final String DELETE = NAMESPACE + ".delete";

    private static final String SELECT_BY_ID = NAMESPACE + ".selectById";

    private static final String SELECT_BY_ORDER_SERVICE_PACK_ID = NAMESPACE + ".selectByOrderServicePackId";


    @Resource(name = Constants.SQL_SESSION_TEMPLATE)
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    /**
     * 根据ID查询订单服务包项目
     *
     * @param id
     *         订单服务包项目ID
     * @return 订单服务包项目
     */
    @Override
    public OrderServicePackItem selectById(BigDecimal id) {
        return getSqlSession().selectOne(SELECT_BY_ID, id);
    }

    /**
     * 根据订单ID查询订单服务包项目
     *
     * @param OrderServicePackId
     *         订单服务包ID
     * @return 订单服务包项目
     */
    @Override
    public List<OrderServicePackItem> selectByOrderServicePackId(BigDecimal OrderServicePackId) {
        return getSqlSession().selectList(SELECT_BY_ORDER_SERVICE_PACK_ID, OrderServicePackId);
    }

    /**
     * 更新订单服务包项目数据
     *
     * @param orderServicePackItem
     *         订单服务包项目数据
     * @return 修改影响的行数
     */
    @Override
    public int update(OrderServicePackItem orderServicePackItem) {
        return getSqlSession().update(UPDATE, orderServicePackItem);
    }

    /**
     * 插入订单服务包项目
     *
     * @param orderServicePackItem
     *         订单服务包项目数据
     * @return 插入的行数
     */
    @Override
    public int insert(OrderServicePackItem orderServicePackItem) {
        return getSqlSession().insert(INSERT, orderServicePackItem);
    }

    /**
     * 插入订单服务包项目列表
     *
     * @param orderServicePackItems
     *         订单服务包项目数据列表
     * @return 插入的行数
     */
    @Override
    public int insertList(List<OrderServicePackItem> orderServicePackItems) {
        return getSqlSession().insert(INSERT_LIST, orderServicePackItems);
    }

    /**
     * 删除订单服务包项目
     *
     * @param id
     *         订单服务包项目id
     * @return 删除的
     */
    @Override
    public int delete(BigDecimal id) {
        return getSqlSession().delete(DELETE, id);
    }


}
