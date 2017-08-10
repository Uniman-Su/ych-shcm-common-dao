package com.ych.shcm.o2o.dao.mybatis;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.ych.core.dao.BaseSqlSessionDaoSupport;
import com.ych.shcm.o2o.dao.ServiceProviderDao;
import com.ych.shcm.o2o.model.Constants;
import com.ych.shcm.o2o.model.ServiceProvider;
import com.ych.shcm.o2o.model.ServiceProviderBusinessArea;

/**
 * 服务商Dao实现
 * <p>
 * Created by U on 2017/7/13.
 */
@Lazy
@Repository("shcm.o2o.dao.mybatis.ServiceProviderDao")
public class ServiceProviderDaoImpl extends BaseSqlSessionDaoSupport implements ServiceProviderDao {

    private static final String NAMESPACE = "com.ych.shcm.o2o.dao.mybatis.ServiceProviderMapper";

    private static final String INSERT = NAMESPACE + ".insert";

    private static final String UPDATE = NAMESPACE + ".update";

    private static final String SELECT_BY_ID = NAMESPACE + ".selectById";

    private static final String SELECT_BY_BUSINESS_AREA_ID = NAMESPACE + ".selectByBusinessAreaId";

    private static final String INSERT_BUSINESS_AREA = NAMESPACE + ".insertBusinessArea";

    private static final String DELETE_BUSINESS_AREA = NAMESPACE + ".deleteBusinessArea";

    @Resource(name = Constants.SQL_SESSION_TEMPLATE)
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    @Override
    public int insert(ServiceProvider serviceProvider) {
        return getSqlSession().insert(INSERT, serviceProvider);
    }

    @Override
    public int update(ServiceProvider serviceProvider) {
        return getSqlSession().update(UPDATE, serviceProvider);
    }

    @Override
    public ServiceProvider selectById(BigDecimal id) {
        return getSqlSession().selectOne(SELECT_BY_ID, id);
    }

    @Cacheable(key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{'BusinessArea', #areaId})", cacheNames = CACHE_NAME, cacheManager = Constants.CACHE_MANAGER)
    @Override
    public ServiceProvider selectByBusinessAreaId(String areaId) {
        return getSqlSession().selectOne(SELECT_BY_BUSINESS_AREA_ID, areaId);
    }

    @Override
    public int insertBusinessArea(List<ServiceProviderBusinessArea> businessAreas) {
        return getSqlSession().insert(INSERT_BUSINESS_AREA, businessAreas);
    }

    @Override
    public int deleteBusinessArea(BigDecimal serviceProviderId) {
        HashMap<String, Object> parameter = new HashMap<>();
        parameter.put("serviceProviderId", serviceProviderId);
        return getSqlSession().delete(DELETE_BUSINESS_AREA, parameter);
    }

    @Override
    public int deleteBusinessArea(BigDecimal serviceProviderId, List<String> areaIds) {
        HashMap<String, Object> parameter = new HashMap<>();
        parameter.put("serviceProviderId", serviceProviderId);
        parameter.put("areaIds", areaIds);
        return getSqlSession().delete(DELETE_BUSINESS_AREA, parameter);
    }
}
