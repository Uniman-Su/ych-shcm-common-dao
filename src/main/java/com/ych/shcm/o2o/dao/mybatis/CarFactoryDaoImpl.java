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
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.ych.core.dao.BaseSqlSessionDaoSupport;
import com.ych.core.model.MultiSortableParameter;
import com.ych.core.model.PagedList;
import com.ych.core.model.SortOrder;
import com.ych.shcm.o2o.dao.CarFactoryDao;
import com.ych.shcm.o2o.model.CarBrandFactory;
import com.ych.shcm.o2o.model.CarFactory;
import com.ych.shcm.o2o.model.Constants;
import com.ych.shcm.o2o.parameter.QueryCarFactoryListParameter;

/**
 * 车型制造厂家的Mapper
 * 
 * @author U
 *
 */
@Lazy
@Repository("shcm.o2o.dao.mybatis.CarFactoryDao")
public class CarFactoryDaoImpl extends BaseSqlSessionDaoSupport implements CarFactoryDao {

    private static final String NAMESPACE = "com.ych.shcm.o2o.dao.mybatis.CarFactoryMapper";

    private static final String SELECT_BY_ID = NAMESPACE + ".selectById";

    private static final String INSERT = NAMESPACE + ".insert";

    private static final String BATCH_INSERT = NAMESPACE + ".batchInsert";

    private static final String IMPORT = NAMESPACE + ".import";

    private static final String UPDATE = NAMESPACE + ".update";

    private static final String DELETE_BY_ID = NAMESPACE + ".deleteById";

    private static final String SELECT_LIST = NAMESPACE + ".selectList";

    private static final String INSERT_BRAND_FACTORY = NAMESPACE + ".insertBrandFactory";

    private static final String BATCH_INSERT_BRAND_FACTORY = NAMESPACE + ".batchInsertBrandFactory";

    private static final String DELETE_BRAND_FACTORY = NAMESPACE + ".deleteBrandFactory";

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
	 * 车型厂家缓存
	 */
	private Cache factoryCache;

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
		factoryCache = cacheManager.getCache(CACHE_NAME);
	}

	@Cacheable(cacheNames = CACHE_NAME, key = "#id", condition = "#lock == false", cacheManager = Constants.CACHE_MANAGER)
	@Override
	public CarFactory selectById(BigDecimal id, boolean lock) {
		HashMap<String, Object> parameter = new HashMap<>();
		parameter.put("id", id);
		parameter.put("lock", lock);
		return getSqlSession().selectOne(SELECT_BY_ID, parameter);
	}

    @Override
	public int insert(CarFactory factory) {
		return getSqlSession().insert(INSERT, factory);
	}

	@Override
	public int insert(List<CarFactory> factories) {
		return getSqlSession().insert(BATCH_INSERT, factories);
	}

	@Override
	public int importFactories(List<CarFactory> factories) {
		int ret = getSqlSession().insert(IMPORT, factories);

		for (CarFactory factory : factories) {
			factoryCache.evict(factory.getId());
		}

		return ret;
	}

	@CacheEvict(cacheNames = CACHE_NAME, key = "#factory.id", cacheManager = Constants.CACHE_MANAGER)
	@Override
	public int update(CarFactory factory) {
		return getSqlSession().update(UPDATE, factory);
	}

	@CacheEvict(cacheNames = CACHE_NAME, key = "#factory.id", cacheManager = Constants.CACHE_MANAGER)
	@Override
	public int delete(CarFactory factory) {
		return getSqlSession().delete(DELETE_BY_ID, factory.getId());
	}

	@Cacheable(cacheNames = CACHE_NAME, key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{'EnabledList', #brandId})", cacheManager = Constants.CACHE_MANAGER)
	@Override
	public List<CarFactory> selectByBrandId(BigDecimal brandId) {
		QueryCarFactoryListParameter parameter = new QueryCarFactoryListParameter();
		parameter.setBrandId(brandId);
		parameter.setEnabled(Boolean.TRUE);
		parameter.setSorts(Arrays.asList(new MultiSortableParameter.SortParameter("firstChar", SortOrder.asc), new MultiSortableParameter.SortParameter("name", SortOrder.asc)));
		convertSortColumnName(parameter, SORT_COLS);
		return getSqlSession().selectList(SELECT_LIST, parameter);
	}

	@Override
	public PagedList<CarFactory> selectPagedList(QueryCarFactoryListParameter parameter) {
		convertSortColumnName(parameter, SORT_COLS);
		return selectPaged(SELECT_LIST, parameter);
	}

	@CacheEvict(cacheNames = CACHE_NAME, key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{'EnabledList', #factory.brandId})", cacheManager = Constants.CACHE_MANAGER)
	@Override
	public int insertBrandFactory(CarBrandFactory factory) {
		return getSqlSession().insert(INSERT_BRAND_FACTORY, factory);
	}

	@Override
	public int insertBrandFactories(List<CarBrandFactory> factories) {
		int ret = getSqlSession().insert(BATCH_INSERT_BRAND_FACTORY, factories);

		HashSet<BigDecimal> brandIds = new HashSet<>();
		for (CarBrandFactory factory : factories) {
			brandIds.add(factory.getBrandId());
		}

		for (BigDecimal brandId : brandIds) {
			factoryCache.evict(ArrayUtils.toString(new Object[]{"EnabledList", brandId}));
		}

		return ret;
	}

	@CacheEvict(cacheNames = CACHE_NAME, key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{'EnabledList', #factory.brandId})", cacheManager = Constants.CACHE_MANAGER)
	@Override
	public int delete(CarBrandFactory factory) {
		return getSqlSession().delete(DELETE_BRAND_FACTORY, factory);
	}

}
