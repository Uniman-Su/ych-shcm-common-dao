package com.ych.shcm.o2o.dao.mybatis;

import java.math.BigDecimal;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang3.ArrayUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.ych.core.dao.BaseSqlSessionDaoSupport;
import com.ych.shcm.o2o.dao.CarSeriesYearDao;
import com.ych.shcm.o2o.model.CarSeriesYear;
import com.ych.shcm.o2o.model.Constants;

/**
 * 车系年份Mapper
 *
 * @author U
 */
@Lazy
@Repository("shcm.o2o.dao.mybatis.CarSeriesYearDao")
public class CarSeriesYearDaoImpl extends BaseSqlSessionDaoSupport implements CarSeriesYearDao {

    private static final String NAMESPACE = "com.ych.shcm.o2o.dao.mybatis.CarSeriesYearMapper";

    private static final String SELECT_BY_ID = NAMESPACE + ".selectById";

    private static final String INSERT = NAMESPACE + ".insert";

    private static final String BATCH_INSERT = NAMESPACE + ".batchInsert";

    private static final String IMPORT = NAMESPACE + ".import";

    private static final String UPDATE = NAMESPACE + ".update";

    private static final String DELETE_BY_ID = NAMESPACE + ".deleteById";

    private static final String SELECT_BY_SERIES_ID = NAMESPACE + ".selectBySeriesId";

    /**
     * 缓存管理器
     */
    @Resource(name = Constants.CACHE_MANAGER)
    private CacheManager cacheManager;

    /**
     * 车系年份缓存
     */
    private Cache seriesYearCache;

    @Resource(name = Constants.SQL_SESSION_TEMPLATE)
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    /**
     * 初始化
     */
    @PostConstruct
    public void init() {
        seriesYearCache = cacheManager.getCache(CACHE_NAME);
    }

    @Cacheable(cacheNames = CACHE_NAME, key = "#id", condition = "#lock == false", cacheManager = Constants.CACHE_MANAGER)
    @Override
    public CarSeriesYear selectById(BigDecimal id, boolean lock) {
        HashMap<String, Object> parameter = new HashMap<>();
        parameter.put("id", id);
        parameter.put("lock", lock);
        return getSqlSession().selectOne(SELECT_BY_ID, parameter);
    }

    @CacheEvict(cacheNames = CACHE_NAME, key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{'EnabledList', #year.seriesId})", cacheManager = Constants.CACHE_MANAGER)
    @Override
    public int insert(CarSeriesYear year) {
        return getSqlSession().insert(INSERT, year);
    }

    @Override
    public int insert(List<CarSeriesYear> years) {
        int ret = getSqlSession().insert(BATCH_INSERT, years);

        HashSet<BigDecimal> seriesIds = new HashSet<>();
        for (CarSeriesYear seriesYear : years) {
            if (seriesYear.isEnabled()) {
                seriesIds.add(seriesYear.getSeriesId());
            }
        }

        for (BigDecimal seriesId : seriesIds) {
            seriesYearCache.evict(ArrayUtils.toString(new Object[]{"EnabledList", seriesId}));
        }

        return ret;
    }

    @Override
    public int importSeriesYears(List<CarSeriesYear> years) {
        int ret = getSqlSession().insert(IMPORT, years);

        HashSet<BigDecimal> seriesIds = new HashSet<>();
        for (CarSeriesYear seriesYear : years) {
            if (seriesYear.isEnabled()) {
                seriesIds.add(seriesYear.getSeriesId());
            }
            seriesYearCache.evict(seriesYear.getId());
        }

        for (BigDecimal seriesId : seriesIds) {
            seriesYearCache.evict(ArrayUtils.toString(new Object[]{"EnabledList", seriesId}));
        }

        return ret;
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = CACHE_NAME, key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{'EnabledList', #year.seriesId})", cacheManager = Constants.CACHE_MANAGER),
            @CacheEvict(cacheNames = CACHE_NAME, key = "#year.id", cacheManager = Constants.CACHE_MANAGER)})
    @Override
    public int update(CarSeriesYear year) {
        return getSqlSession().update(UPDATE, year);
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = CACHE_NAME, key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{'EnabledList', #year.seriesId})", cacheManager = Constants.CACHE_MANAGER),
            @CacheEvict(cacheNames = CACHE_NAME, key = "#year.id", cacheManager = Constants.CACHE_MANAGER)})
    @Override
    public int delete(CarSeriesYear year) {
        return getSqlSession().delete(DELETE_BY_ID, year.getId());
    }

    @Cacheable(cacheNames = CACHE_NAME, key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{'EnabledList', #seriesId})", cacheManager = Constants.CACHE_MANAGER)
    @Override
    public List<CarSeriesYear> selectBySeriesId(BigDecimal seriesId) {
        return getSqlSession().selectList(SELECT_BY_SERIES_ID, seriesId);
    }

}
