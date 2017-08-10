package com.ych.shcm.o2o.dao.mybatis;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.ych.core.dao.BaseSqlSessionDaoSupport;
import com.ych.shcm.o2o.dao.UserWechatInfoDao;
import com.ych.shcm.o2o.model.Constants;
import com.ych.shcm.o2o.model.UserWechatInfo;

/**
 * 用户微信信息Dao实现
 * <p>
 * Created by U on 2017/7/13.
 */
@Lazy
@Repository("shcm.o2o.dao.mybatis.UserWechatInfoDao")
public class UserWechatInfoDaoImpl extends BaseSqlSessionDaoSupport implements UserWechatInfoDao {

    private static final String NAMESPACE = "com.ych.shcm.o2o.dao.mybatis.UserWechatInfoMapper";

    private static final String INSERT = NAMESPACE + ".insert";

    private static final String UPDATE = NAMESPACE + ".update";

    private static final String SELECT_BY_ID = NAMESPACE + ".selectById";

    private static final String SELECT_BY_USER_ID = NAMESPACE + ".selectByUserId";

    private static final String SELECT_BY_OPEN_ID = NAMESPACE + ".selectByOpenId";

    @Resource(name = Constants.SQL_SESSION_TEMPLATE)
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    @Override
    public int insert(UserWechatInfo userWechatInfo) {
        return getSqlSession().insert(INSERT, userWechatInfo);
    }

    @Override
    public int update(UserWechatInfo userWechatInfo) {
        return getSqlSession().update(UPDATE, userWechatInfo);
    }

    @Override
    public UserWechatInfo selectById(BigDecimal id) {
        return getSqlSession().selectOne(SELECT_BY_ID, id);
    }

    @Override
    public UserWechatInfo selectByUserId(BigDecimal userId) {
        return getSqlSession().selectOne(SELECT_BY_USER_ID, userId);
    }

    @Override
    public UserWechatInfo selectByOpenId(String openId) {
        return getSqlSession().selectOne(SELECT_BY_OPEN_ID, openId);
    }
}
