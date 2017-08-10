package com.ych.shcm.o2o.dao.mybatis;

import com.ych.core.dao.BaseSqlSessionDaoSupport;
import com.ych.core.model.PagedList;
import com.ych.shcm.o2o.dao.CarDao;
import com.ych.shcm.o2o.model.Car;
import com.ych.shcm.o2o.model.CarExpiredMaintenanceInfo;
import com.ych.shcm.o2o.model.CarModel;
import com.ych.shcm.o2o.model.Constants;
import com.ych.shcm.o2o.parameter.QueryCarExpiredMaintenanceParameter;
import com.ych.shcm.o2o.parameter.QueryCarParameter;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 车辆的Dao实现
 * <p>
 * Created by U on 2017/7/10.
 */
@Lazy
@Repository("shcm.o2o.dao.mybatis.CarDao")
public class CarDaoImpl extends BaseSqlSessionDaoSupport implements CarDao {

    private static final String NAMESPACE = "com.ych.shcm.o2o.dao.mybatis.CarMapper";

    private static final String INSERT = NAMESPACE + ".insert";

    private static final String UPDATE = NAMESPACE + ".update";

    private static final String SELECT_BY_ID = NAMESPACE + ".selectById";

    private static final String SELECT_BY_VIN = NAMESPACE + ".selectByVin";

    private static final String SELECT_LIST = NAMESPACE + ".selectList";

    private static final String INSERT_EXPIRED_MAINTENANCE_INFO = NAMESPACE + ".insertExpiredMaintenanceInfo";

    private static final String SELECT_EXPIRED_MAINTENANCE_INFO_BY_ID = NAMESPACE + ".selectExpiredMaintenanceInfoById";

    private static final String SELECT_EXPIRED_MAINTENANCE_INFO_BY_CAR_ID_AND_EFFECT_TIME = NAMESPACE + ".selectExpiredMaintenanceInfoByCarIdAndEffectTime";

    private static final String SELECT_EXPIRED_MAINTENANCE_INFO_LIST = NAMESPACE + ".selectExpiredMaintenanceInfoList";

    private static final String SELECT_CARS_OF_USER = NAMESPACE + ".selectCarsOfUser";

    @Resource(name = Constants.SQL_SESSION_TEMPLATE)
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    @Caching(evict = {
            @CacheEvict(key = "#car.id", cacheNames = CACHE_NAME, cacheManager = Constants.CACHE_MANAGER, condition = "#result > 0"),
            @CacheEvict(key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{'VIN', #car.vin})", cacheNames = CACHE_NAME, cacheManager = Constants.CACHE_MANAGER, condition = "#result > 0"),
    })
    @Override
    public int insert(Car car) {
        return getSqlSession().insert(INSERT, car);
    }

    @Caching(evict = {
            @CacheEvict(key = "#car.id", cacheNames = CACHE_NAME, cacheManager = Constants.CACHE_MANAGER, condition = "#result > 0"),
            @CacheEvict(key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{'VIN', #car.vin})", cacheNames = CACHE_NAME, cacheManager = Constants.CACHE_MANAGER, condition = "#result > 0"),
    })
    @Override
    public int update(Car car) {
        return getSqlSession().update(UPDATE, car);
    }

    @Cacheable(cacheNames = CACHE_NAME, cacheManager = Constants.CACHE_MANAGER)
    @Override
    public Car selectById(BigDecimal id) {
        return getSqlSession().selectOne(SELECT_BY_ID, id);
    }

    @Cacheable(key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{'VIN', #vin})", cacheNames = CACHE_NAME, cacheManager = Constants.CACHE_MANAGER)
    @Override
    public Car selectByVin(String vin) {
        return getSqlSession().selectOne(SELECT_BY_VIN, vin);
    }

    @Override
    public PagedList<Car> selectPagedList(QueryCarParameter parameter) {
        return selectPaged(SELECT_LIST, parameter);
    }

    @Override
    public int insertExpiredMaintenanceInfo(CarExpiredMaintenanceInfo expiredMaintenanceInfo) {
        return getSqlSession().insert(INSERT_EXPIRED_MAINTENANCE_INFO, expiredMaintenanceInfo);
    }

    @Override
    public CarExpiredMaintenanceInfo selectExpiredMaintenanceInfoById(BigDecimal id) {
        return getSqlSession().selectOne(SELECT_EXPIRED_MAINTENANCE_INFO_BY_ID, id);
    }

    @Override
    public CarExpiredMaintenanceInfo selectExpiredMaintenanceInfoByCarIdAndEffectTime(BigDecimal carId, Date effectTime) {
        HashMap<String, Object> parameter = new HashMap<>();
        parameter.put("carId", carId);
        parameter.put("effectTime", effectTime);

        return getSqlSession().selectOne(SELECT_EXPIRED_MAINTENANCE_INFO_BY_CAR_ID_AND_EFFECT_TIME, parameter);
    }

    @Override
    public PagedList<QueryCarExpiredMaintenanceParameter> selectPagedExpiredMaintenanceInfoList(QueryCarExpiredMaintenanceParameter parameter) {
        return selectPaged(SELECT_EXPIRED_MAINTENANCE_INFO_LIST, parameter);
    }

    @Override
    public List<CarModel> selectCarsOfUser(BigDecimal userId) {
        return getSqlSession().selectList(SELECT_EXPIRED_MAINTENANCE_INFO_LIST, userId);
    }
}
