package com.ych.shcm.o2o.dao.mybatis;

import java.math.BigDecimal;
import java.util.HashMap;
import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.ych.core.dao.BaseSqlSessionDaoSupport;
import com.ych.shcm.o2o.dao.OperatorThirdAuthDao;
import com.ych.shcm.o2o.model.Constants;
import com.ych.shcm.o2o.model.OperatorThirdAuth;
import com.ych.shcm.o2o.model.ThirdAuthType;

/**
 * 第三方认证信息Dao的实现
 * <p>
 * Created by U on 2017/7/13.
 */
@Lazy
@Repository("shcm.o2o.dao.mybatis.OperatorThirdAuthDao")
public class OperatorThirdAuthDaoImpl extends BaseSqlSessionDaoSupport implements OperatorThirdAuthDao {

    private static final String NAMESPACE = "com.ych.shcm.o2o.dao.mybatis.OperatorThirdAuthMapper";

    private static final String INSERT = NAMESPACE + ".insert";

    private static final String SELECT_BY_THIRD_TYPE = NAMESPACE + ".selectByThirdType";

    private static final String SELECT_BY_THIRD_ID = NAMESPACE + ".selectByThirdId";

    @Resource(name = Constants.SQL_SESSION_TEMPLATE)
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    @Override
    public int insert(OperatorThirdAuth operatorThirdAuth) {
        return getSqlSession().insert(INSERT, operatorThirdAuth);
    }

    @Override
    public OperatorThirdAuth selectByThirdType(BigDecimal operatorId, ThirdAuthType thirdAuthType) {
        HashMap<String, Object> parameter = new HashMap<>();
        parameter.put("operatorId", operatorId);
        parameter.put("type", thirdAuthType);
        return getSqlSession().selectOne(SELECT_BY_THIRD_TYPE, parameter);
    }

    @Override
    public OperatorThirdAuth selectByThirdId(ThirdAuthType thirdAuthType, String thirdId) {
        HashMap<String, Object> parameter = new HashMap<>();
        parameter.put("type", thirdAuthType);
        parameter.put("thirdId", thirdId);
        return getSqlSession().selectOne(SELECT_BY_THIRD_ID, parameter);
    }
}
