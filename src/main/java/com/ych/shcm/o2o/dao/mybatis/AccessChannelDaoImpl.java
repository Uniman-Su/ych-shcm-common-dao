package com.ych.shcm.o2o.dao.mybatis;

import java.math.BigDecimal;
import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.ych.core.dao.BaseSqlSessionDaoSupport;
import com.ych.shcm.o2o.dao.AccessChannelDao;
import com.ych.shcm.o2o.model.AccessChannel;
import com.ych.shcm.o2o.model.Constants;

/**
 * 访问渠道Dao实现
 *
 * Created by U on 2017/7/5.
 */
@Lazy
@Repository("shcm.o2o.dao.mybatis.AccessChannelDao")
public class AccessChannelDaoImpl extends BaseSqlSessionDaoSupport implements AccessChannelDao {

    private static final String NAMESPACE = "com.ych.shcm.o2o.dao.mybatis.AccessChannelMapper";

    private static final String SELECT_BY_ID = NAMESPACE + ".selectById";

    private static final String SELECT_BY_CODE = NAMESPACE + ".selectByCode";

    @Resource(name = Constants.SQL_SESSION_TEMPLATE)
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    @Cacheable(cacheNames = CACHE_NAME, cacheManager = Constants.CACHE_MANAGER)
    public AccessChannel selectById(BigDecimal id) {
        return getSqlSession().selectOne(SELECT_BY_ID, id);
    }

    @Cacheable(key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{'code', #code})", cacheNames = CACHE_NAME, cacheManager = Constants.CACHE_MANAGER)
    public AccessChannel selectByCode(String code) {
        return getSqlSession().selectOne(SELECT_BY_CODE, code);
    }
}
