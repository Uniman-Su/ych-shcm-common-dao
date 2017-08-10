package com.ych.shcm.o2o.dao.mybatis;

import java.math.BigDecimal;
import java.util.HashMap;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.ych.core.dao.BaseSqlSessionDaoSupport;
import com.ych.shcm.o2o.dao.UserThirdAuthDao;
import com.ych.shcm.o2o.model.Constants;
import com.ych.shcm.o2o.model.ThirdAuthType;
import com.ych.shcm.o2o.model.UserThirdAuth;

/**
 * 第三方认证信息Dao的实现
 * <p>
 * Created by U on 2017/7/13.
 */
@Lazy
@Repository("shcm.o2o.dao.mybatis.UserThirdAuthDao")
public class UserThirdAuthDaoImpl extends BaseSqlSessionDaoSupport implements UserThirdAuthDao {

    private static final String NAMESPACE = "com.ych.shcm.o2o.dao.mybatis.UserThirdAuthMapper";

    private static final String INSERT = NAMESPACE + ".insert";

    private static final String SELECT_BY_THIRD_TYPE = NAMESPACE + ".selectByThirdType";

    private static final String SELECT_BY_THIRD_ID = NAMESPACE + ".selectByThirdId";

    @Resource(name = Constants.SQL_SESSION_TEMPLATE)
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    @Override
    public int insert(UserThirdAuth userThirdAuth) {
        return getSqlSession().insert(INSERT, userThirdAuth);
    }

    @Override
    public UserThirdAuth selectByThirdType(BigDecimal userId, ThirdAuthType thirdAuthType) {
        HashMap<String, Object> parameter = new HashMap<>();
        parameter.put("userId", userId);
        parameter.put("type", thirdAuthType);
        return getSqlSession().selectOne(SELECT_BY_THIRD_TYPE, parameter);
    }

    @Override
    public UserThirdAuth selectByThirdId(ThirdAuthType thirdAuthType, String thirdId) {
        HashMap<String, Object> parameter = new HashMap<>();
        parameter.put("type", thirdAuthType);
        parameter.put("thirdId", thirdId);
        return getSqlSession().selectOne(SELECT_BY_THIRD_ID, parameter);
    }
}
