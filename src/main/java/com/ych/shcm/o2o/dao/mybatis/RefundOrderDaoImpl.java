package com.ych.shcm.o2o.dao.mybatis;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.ych.core.dao.BaseSqlSessionDaoSupport;
import com.ych.shcm.o2o.dao.RefundOrderDao;
import com.ych.shcm.o2o.model.Constants;
import com.ych.shcm.o2o.model.RefundOrder;

/**
 * 退款单的Dao实现
 */
@Lazy
@Repository("shcm.o2o.dao.mybatis.RefundOrderDao")
public class RefundOrderDaoImpl extends BaseSqlSessionDaoSupport implements RefundOrderDao {

    private static final String NAMESPACE = "com.ych.shcm.o2o.dao.mybatis.RefundOrderMapper";

    private static final String INSERT = NAMESPACE + ".insert";

    private static final String UPDATE = NAMESPACE + ".update";

    @Resource(name = Constants.SQL_SESSION_TEMPLATE)
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    @Override
    public int insert(RefundOrder refundOrder) {
        return getSqlSession().insert(INSERT, refundOrder);
    }

    @Override
    public int udpate(RefundOrder refundOrder) {
        return getSqlSession().update(UPDATE, refundOrder);
    }
}
