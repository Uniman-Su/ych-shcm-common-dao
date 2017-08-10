package com.ych.shcm.o2o.dao.mybatis;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.ych.core.dao.BaseSqlSessionDaoSupport;
import com.ych.shcm.o2o.dao.OperatorDao;
import com.ych.shcm.o2o.model.Constants;
import com.ych.shcm.o2o.model.Operator;

/**
 * 管理员Dao实现
 * <p>
 * Created by U on 2017/7/11.
 */
@Lazy
@Repository("shcm.o2o.dao.mybatis.OperatorDao")
public class OperatorDaoImpl extends BaseSqlSessionDaoSupport implements OperatorDao {

    private static final String NAMESPACE = "com.ych.shcm.o2o.dao.mybatis.OperatorMapper";

    private static final String INSERT = NAMESPACE + ".insert";

    private static final String UPDATE = NAMESPACE + ".update";

    private static final String SELECT_BY_ID = NAMESPACE + ".selectById";

    private static final String SELECT_BY_USERNAME = NAMESPACE + ".selectByUsername";

    @Resource(name = Constants.SQL_SESSION_TEMPLATE)
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    @Override
    public int insert(Operator operator) {
        return getSqlSession().insert(INSERT, operator);
    }

    @Override
    public int update(Operator operator) {
        return getSqlSession().update(UPDATE, operator);
    }

    @Override
    public Operator selectById(BigDecimal id) {
        return getSqlSession().selectOne(SELECT_BY_ID, id);
    }

    @Override
    public Operator selectByUsername(String username) {
        return getSqlSession().selectOne(SELECT_BY_USERNAME, username);
    }
}
