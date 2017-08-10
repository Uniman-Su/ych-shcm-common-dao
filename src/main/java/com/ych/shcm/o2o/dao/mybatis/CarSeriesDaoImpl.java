package com.ych.shcm.o2o.dao.mybatis;

import java.math.BigDecimal;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
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
import org.springframework.util.Assert;

import com.ych.core.dao.BaseSqlSessionDaoSupport;
import com.ych.core.model.MultiSortableParameter;
import com.ych.core.model.PagedList;
import com.ych.shcm.o2o.dao.CarSeriesDao;
import com.ych.shcm.o2o.model.CarSeries;
import com.ych.shcm.o2o.model.Constants;
import com.ych.shcm.o2o.parameter.QueryCarSeriesListParameter;

@Lazy
@Repository("shcm.o2o.dao.mybatis.CarSeriesDao")
public class CarSeriesDaoImpl extends BaseSqlSessionDaoSupport implements CarSeriesDao {

    private static final String NAMESPACE = "com.ych.shcm.o2o.parameter.CarSeriesMapper";

    private static final String SELECT_BY_ID = NAMESPACE + ".selectById";

    private static final String INSERT = NAMESPACE + ".insert";

    private static final String BATCH_INSERT = NAMESPACE + ".batchInsert";

    private static final String IMPORT = NAMESPACE + ".import";

    private static final String UPDATE = NAMESPACE + ".update";

    private static final String DELETE_BY_ID = NAMESPACE + ".deleteById";

    private static final String SELECT_LIST = NAMESPACE + ".selectList";

    /**
     * 可排序列名映射
     */
    private static final Map<String, String> SORT_COLS;

    static {
        HashMap<String, String> sortCols = new HashMap<>();
        sortCols.put("name", "cs.name");
        sortCols.put("firstChar", "cs.first_char");
        sortCols.put("brandFirstChar", "cb.first_char");
        sortCols.put("brandName", "cb.name");
        sortCols.put("factoryFirstChar", "cf.first_char");
        sortCols.put("factoryName", "cf.name");
        SORT_COLS = Collections.unmodifiableMap(sortCols);
    }

    /**
     * 缓存管理器
     */
    @Resource(name = Constants.CACHE_MANAGER)
    private CacheManager cacheManager;

    /**
     * 车系缓存
     */
    private Cache seriesCache;

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
        seriesCache = cacheManager.getCache(CACHE_NAME);
    }

    @Cacheable(cacheNames = CACHE_NAME, key = "#id", condition = "#lock == false", cacheManager = Constants.CACHE_MANAGER)
    @Override
    public CarSeries selectById(BigDecimal id, boolean lock) {
        HashMap<String, Object> parameter = new HashMap<>();
        parameter.put("id", id);
        parameter.put("lock", lock);
        return getSqlSession().selectOne(SELECT_BY_ID, parameter);
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = CACHE_NAME, key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{'EnabledListByBrand', #series.brandId})", cacheManager = Constants.CACHE_MANAGER),
            @CacheEvict(cacheNames = CACHE_NAME, key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{'EnabledListByFactory', #series.factoryId})", cacheManager = Constants.CACHE_MANAGER),
            @CacheEvict(cacheNames = CACHE_NAME, key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{'EnabledList', #series.brandId, #series.factoryId})", cacheManager = Constants.CACHE_MANAGER)})
    @Override
    public int insert(CarSeries series) {
        return getSqlSession().insert(INSERT, series);
    }

    @Override
    public int insert(List<CarSeries> serieses) {
        int ret = getSqlSession().insert(BATCH_INSERT, serieses);

        HashSet<BigDecimal> brandIds = new HashSet<>(), factoryIds = new HashSet<>();
        HashSet<Pair<BigDecimal, BigDecimal>> brandFacPairs = new HashSet<>();

        for (CarSeries series : serieses) {
            brandIds.add(series.getBrandId());
            factoryIds.add(series.getFactoryId());
            brandFacPairs.add(Pair.of(series.getBrandId(), series.getFactoryId()));
        }

        for (BigDecimal brandId : brandIds) {
            seriesCache.evict(ArrayUtils.toString(new Object[]{"EnabledListByBrand", brandId}));
        }

        for (BigDecimal factoryId : factoryIds) {
            seriesCache.evict(ArrayUtils.toString(new Object[]{"EnabledListByFactory", factoryId}));
        }

        for (Pair<BigDecimal, BigDecimal> brandFacPair : brandFacPairs) {
            seriesCache.evict(ArrayUtils.toString(new Object[]{"EnabledList", brandFacPair.getLeft(), brandFacPair.getRight()}));
        }

        return ret;
    }

    @Override
    public int importSerieses(List<CarSeries> serieses) {
        int ret = getSqlSession().insert(IMPORT, serieses);

        HashSet<BigDecimal> brandIds = new HashSet<>(), factoryIds = new HashSet<>();
        HashSet<Pair<BigDecimal, BigDecimal>> brandFacPairs = new HashSet<>();

        for (CarSeries series : serieses) {
            brandIds.add(series.getBrandId());
            factoryIds.add(series.getFactoryId());
            brandFacPairs.add(Pair.of(series.getBrandId(), series.getFactoryId()));
            seriesCache.evict(series.getId());
        }

        for (BigDecimal brandId : brandIds) {
            seriesCache.evict(ArrayUtils.toString(new Object[]{"EnabledListByBrand", brandId}));
        }

        for (BigDecimal factoryId : factoryIds) {
            seriesCache.evict(ArrayUtils.toString(new Object[]{"EnabledListByFactory", factoryId}));
        }

        for (Pair<BigDecimal, BigDecimal> brandFacPair : brandFacPairs) {
            seriesCache.evict(ArrayUtils.toString(new Object[]{"EnabledList", brandFacPair.getLeft(), brandFacPair.getRight()}));
        }

        return ret;
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = CACHE_NAME, key = "#series.id", cacheManager = Constants.CACHE_MANAGER),
            @CacheEvict(cacheNames = CACHE_NAME, key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{'EnabledListByBrand', #series.brandId})", cacheManager = Constants.CACHE_MANAGER),
            @CacheEvict(cacheNames = CACHE_NAME, key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{'EnabledListByFactory', #series.factoryId})", cacheManager = Constants.CACHE_MANAGER),
            @CacheEvict(cacheNames = CACHE_NAME, key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{'EnabledList', #series.brandId, #series.factoryId})", cacheManager = Constants.CACHE_MANAGER)})
    @Override
    public int update(CarSeries series) {
        return getSqlSession().update(UPDATE, series);
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = CACHE_NAME, key = "#series.id", cacheManager = Constants.CACHE_MANAGER),
            @CacheEvict(cacheNames = CACHE_NAME, key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{'EnabledListByBrand', #series.brandId})", cacheManager = Constants.CACHE_MANAGER),
            @CacheEvict(cacheNames = CACHE_NAME, key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{'EnabledListByFactory', #series.factoryId})", cacheManager = Constants.CACHE_MANAGER),
            @CacheEvict(cacheNames = CACHE_NAME, key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{'EnabledList', #series.brandId, #series.factoryId})", cacheManager = Constants.CACHE_MANAGER)})
    @Override
    public int delete(CarSeries series) {
        return getSqlSession().delete(DELETE_BY_ID, series.getId());
    }

    @Caching(cacheable = {
            @Cacheable(cacheNames = CACHE_NAME, key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{'EnabledListByBrand', #brandId})", condition = "#brandId != null and #factoryId == null", cacheManager = Constants.CACHE_MANAGER),
            @Cacheable(cacheNames = CACHE_NAME, key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{'EnabledListByFactory', #factoryId})", condition = "#brandId == null and #factoryId != null", cacheManager = Constants.CACHE_MANAGER),
            @Cacheable(cacheNames = CACHE_NAME, key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{'EnabledList', #brandId, #factoryId})", condition = "#brandId != null and #factoryId != null", cacheManager = Constants.CACHE_MANAGER)})
    @Override
    public List<CarSeries> selectList(BigDecimal brandId, BigDecimal factoryId) {
        Assert.isTrue(brandId != null || factoryId != null, "brandId and factoryId can not be both null");

        QueryCarSeriesListParameter parameter = new QueryCarSeriesListParameter();
        parameter.setBrandId(brandId);
        parameter.setFactoryId(factoryId);
        parameter.setEnabled(Boolean.TRUE);
        parameter.setSorts(Arrays.asList(new MultiSortableParameter.SortParameter("firstChar"), new MultiSortableParameter.SortParameter("name")));

        convertSortColumnName(parameter, SORT_COLS);
        return getSqlSession().selectList(SELECT_LIST, parameter);
    }

    /**
     * 设置是否需要联合品牌查询
     *
     * @param parameter
     *         查询参数
     */
    private void setJoinBrand(QueryCarSeriesListParameter parameter) {
        boolean join = false;
        if (CollectionUtils.isNotEmpty(parameter.getSorts())) {
            for (MultiSortableParameter.SortParameter sort : parameter.getSorts()) {
                if ("brandFirstChar".equals(sort.getSort()) || "brandName".equals(sort.getSort())) {
                    join = true;
                    break;
                }
            }
        }
        parameter.setJoinBrand(join);
    }

    /**
     * 设置是否需要联合厂家查询
     *
     * @param parameter
     *         查询参数
     */
    private void setJoinFactory(QueryCarSeriesListParameter parameter) {
        boolean join = false;
        if (CollectionUtils.isNotEmpty(parameter.getSorts())) {
            for (MultiSortableParameter.SortParameter sort : parameter.getSorts()) {
                if ("factoryFirstChar".equals(sort.getSort()) || "factoryName".equals(sort.getSort())) {
                    join = true;
                    break;
                }
            }
        }
        parameter.setJoinFactory(join);
    }

    @Override
    public PagedList<CarSeries> selectPagedList(QueryCarSeriesListParameter parameter) {
        setJoinBrand(parameter);
        setJoinFactory(parameter);
        convertSortColumnName(parameter, SORT_COLS);
        return selectPaged(SELECT_LIST, parameter);
    }

}
