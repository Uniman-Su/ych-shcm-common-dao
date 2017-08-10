package com.ych.shcm.o2o.dao.mybatis;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.ych.core.dao.BaseSqlSessionDaoSupport;
import com.ych.shcm.o2o.dao.CarModelDao;
import com.ych.shcm.o2o.model.CarModel;
import com.ych.shcm.o2o.model.Constants;

/**
 * 车型的Mapper
 *
 * @author U
 */
@Lazy
@Repository("shcm.o2o.dao.mybatis.CarModelDao")
public class CarModelDaoImpl extends BaseSqlSessionDaoSupport implements CarModelDao {

    private static final String NAMESPACE = "com.ych.shcm.o2o.dao.mybatis.CarModelMapper";

    private static final String SELECT_BY_ID = NAMESPACE + ".selectById";

    private static final String INSERT = NAMESPACE + ".insert";

    private static final String BATCH_INSERT = NAMESPACE + ".batchInsert";

    private static final String IMPORT = NAMESPACE + ".import";

    private static final String UPDATE = NAMESPACE + ".update";

    private static final String DELETE_BY_ID = NAMESPACE + ".deleteById";

    private static final String SELECT_BY_SERIES_YEAR = NAMESPACE + ".selectBySeriesYear";

    /**
     * 缓存管理器
     */
    @Resource(name = Constants.CACHE_MANAGER)
    private CacheManager cacheManager;

    /**
     * 车型缓存
     */
    private Cache modelCache;

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
        modelCache = cacheManager.getCache(CAR_MODEL_CACHE_NAME);
    }

    @Cacheable(cacheNames = CAR_MODEL_CACHE_NAME, key = "#id", condition = "#lock == false", cacheManager = Constants.CACHE_MANAGER)
    @Override
    public CarModel selectById(BigDecimal id, boolean lock) {
        HashMap<String, Object> parameter = new HashMap<>();
        parameter.put("id", id);
        parameter.put("lock", lock);
        return getSqlSession().selectOne(SELECT_BY_ID, parameter);
    }

    @CacheEvict(cacheNames = CAR_MODEL_CACHE_NAME, key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{'EnabledList', #model.seriesId, #model.year})", condition = "#model.enabled == true", cacheManager = Constants.CACHE_MANAGER)
    @Override
    public int insert(CarModel model) {
        return getSqlSession().insert(INSERT, model);
    }

    @Override
    public int insert(List<CarModel> models) {
        int ret = getSqlSession().insert(BATCH_INSERT, models);

        HashSet<Pair<BigDecimal, Integer>> seriesYears = new HashSet<>();
        for (CarModel model : models) {
            if (model.isEnabled()) {
                seriesYears.add(Pair.of(model.getSeriesId(), model.getYear()));
            }
        }

        for (Pair<BigDecimal, Integer> seriesYear : seriesYears) {
            modelCache.evict(ArrayUtils.toString(new Object[]{"EnabledList", seriesYear.getLeft(), seriesYear.getRight()}));
        }

        return ret;
    }

    @Override
    public int importModels(List<CarModel> models) {
        int ret = getSqlSession().insert(IMPORT, models);

        HashSet<Pair<BigDecimal, Integer>> seriesYears = new HashSet<>();
        for (CarModel model : models) {
            if (model.isEnabled()) {
                seriesYears.add(Pair.of(model.getSeriesId(), model.getYear()));
            }
            modelCache.evict(model.getId());
        }

        for (Pair<BigDecimal, Integer> seriesYear : seriesYears) {
            modelCache.evict(ArrayUtils.toString(new Object[]{"EnabledList", seriesYear.getLeft(), seriesYear.getRight()}));
        }

        return ret;
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = CAR_MODEL_CACHE_NAME, key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{'EnabledList', #model.seriesId, #model.year})", cacheManager = Constants.CACHE_MANAGER),
            @CacheEvict(cacheNames = CAR_MODEL_CACHE_NAME, key = "#model.id", cacheManager = Constants.CACHE_MANAGER)})
    @Override
    public int update(CarModel model) {
        return getSqlSession().update(UPDATE, model);
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = CAR_MODEL_CACHE_NAME, key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{'EnabledList', #model.seriesId, #model.year})", cacheManager = Constants.CACHE_MANAGER),
            @CacheEvict(cacheNames = CAR_MODEL_CACHE_NAME, key = "#model.id", cacheManager = Constants.CACHE_MANAGER)})
    @Override
    public int delete(CarModel model) {
        return getSqlSession().delete(DELETE_BY_ID, model.getId());
    }

    @Cacheable(cacheNames = CAR_MODEL_CACHE_NAME, key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{'EnabledList', #seriesId, #year})", condition = "#lock == false", cacheManager = Constants.CACHE_MANAGER)
    @Override
    public List<CarModel> selectBySeriesYear(BigDecimal seriesId, int year, boolean lock) {
        HashMap<String, Object> parameter = new HashMap<>();
        parameter.put("seriesId", seriesId);
        parameter.put("year", year);
        parameter.put("lock", lock);
        return getSqlSession().selectList(SELECT_BY_SERIES_YEAR, parameter);
    }

}
