package com.ych.shcm.o2o.dao.mybatis;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.ych.core.dao.BaseSqlSessionDaoSupport;
import com.ych.shcm.o2o.dao.PayFeeConfigDao;
import com.ych.shcm.o2o.model.Constants;
import com.ych.shcm.o2o.model.PayChannel;
import com.ych.shcm.o2o.model.PayFeeConfig;

/**
 * 支付费用配置Dao实现
 *
 * Created by U on 2017/7/11.
 */
@Lazy
@Repository("shcm.o2o.dao.mybatis.PayFeeConfigDao")
public class PayFeeConfigDaoImpl extends BaseSqlSessionDaoSupport implements PayFeeConfigDao {

    private static final String NAMESPACE = "com.ych.shcm.o2o.dao.mybatis.PayFeeConfigMapper";

    private static final String SELECT_BY_PAY_CHANNEL = NAMESPACE + ".selectByPayChannel";

    @Resource(name = Constants.SQL_SESSION_TEMPLATE)
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    @Cacheable(key = "#payChannel.name()", cacheNames = CACHE_NAME, cacheManager = Constants.CACHE_MANAGER)
    @Override
    public PayFeeConfig selectByPayChannel(PayChannel payChannel) {
        return getSqlSession().selectOne(SELECT_BY_PAY_CHANNEL, payChannel);
    }
}
