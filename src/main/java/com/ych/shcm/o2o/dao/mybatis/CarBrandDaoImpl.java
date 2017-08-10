package com.ych.shcm.o2o.dao.mybatis;

import java.math.BigDecimal;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.ych.core.dao.BaseSqlSessionDaoSupport;
import com.ych.core.model.MultiSortableParameter;
import com.ych.core.model.PagedList;
import com.ych.core.model.SortOrder;
import com.ych.shcm.o2o.dao.CarBrandDao;
import com.ych.shcm.o2o.model.CarBrand;
import com.ych.shcm.o2o.model.Constants;
import com.ych.shcm.o2o.parameter.QueryCarBrandListParameter;

/**
 * 车型品牌的Mapper
 *
 * @author U
 */
@Lazy
@Repository("shcm.o2o.dao.mybatis.CarBrandDao")
public class CarBrandDaoImpl extends BaseSqlSessionDaoSupport implements CarBrandDao {

    private static final String NAMESPACE = "com.ych.shcm.o2o.dao.mybatis.CarBrandMapper";

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
        sortCols.put("name", "name");
        sortCols.put("firstChar", "first_char");
        SORT_COLS = Collections.unmodifiableMap(sortCols);
    }

    /**
     * 缓存管理器
     */
    @Resource(name = Constants.CACHE_MANAGER)
    private CacheManager cacheManager;

    /**
     * 车型品牌缓存
     */
    private Cache brandCache;

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
        brandCache = cacheManager.getCache(CACHE_NAME);
    }

    @Cacheable(cacheNames = CACHE_NAME, condition = "#lock == false", key = "#id", cacheManager = Constants.CACHE_MANAGER)
    @Override
    public CarBrand selectById(BigDecimal id, boolean lock) {
        HashMap<String, Object> parameter = new HashMap<>();
        parameter.put("id", id);
        parameter.put("lock", lock);
        return getSqlSession().selectOne(SELECT_BY_ID, parameter);
    }

    @CacheEvict(cacheNames = CACHE_NAME, key = "'EnabledList'", cacheManager = Constants.CACHE_MANAGER)
    @Override
    public int insert(CarBrand brand) {
        return getSqlSession().insert(INSERT, brand);
    }

    @CacheEvict(cacheNames = CACHE_NAME, key = "'EnabledList'", cacheManager = Constants.CACHE_MANAGER)
    @Override
    public int insert(List<CarBrand> brands) {
        return getSqlSession().insert(BATCH_INSERT, brands);
    }

    @CacheEvict(cacheNames = CACHE_NAME, key = "'EnabledList'", cacheManager = Constants.CACHE_MANAGER)
    @Override
    public int importBrands(List<CarBrand> brands) {
        int ret = getSqlSession().insert(IMPORT, brands);

        // 为了防止已经存在的数据被更新因此需要清理缓存
        for (CarBrand brand : brands) {
            brandCache.evict(brand.getId());
        }

        return ret;
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = CACHE_NAME, key = "'EnabledList'", cacheManager = Constants.CACHE_MANAGER),
            @CacheEvict(cacheNames = CACHE_NAME, key = "#brand.id")})
    @Override
    public int update(CarBrand brand) {
        return getSqlSession().update(UPDATE, brand);
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = CACHE_NAME, key = "'EnabledList'", cacheManager = Constants.CACHE_MANAGER),
            @CacheEvict(cacheNames = CACHE_NAME, key = "#brand.id", cacheManager = Constants.CACHE_MANAGER)})
    @Override
    public int delete(CarBrand brand) {
        return getSqlSession().update(DELETE_BY_ID, brand.getId());
    }

    @Cacheable(cacheNames = CACHE_NAME, key = "'EnabledList'", cacheManager = Constants.CACHE_MANAGER)
    @Override
    public List<CarBrand> selectList() {
        QueryCarBrandListParameter parameter = new QueryCarBrandListParameter();

        ArrayList<MultiSortableParameter.SortParameter> sorts = new ArrayList<>();
        sorts.add(new MultiSortableParameter.SortParameter("firstChar", SortOrder.asc));
        sorts.add(new MultiSortableParameter.SortParameter("name", SortOrder.asc));

        parameter.setEnabled(Boolean.TRUE);
        convertSortColumnName(parameter, SORT_COLS);
        return getSqlSession().selectList(SELECT_LIST, parameter);
    }

    @Override
    public PagedList<CarBrand> selectPagedList(QueryCarBrandListParameter parameter) {
        convertSortColumnName(parameter, SORT_COLS);
        return selectPaged(SELECT_LIST, parameter);
    }

}
