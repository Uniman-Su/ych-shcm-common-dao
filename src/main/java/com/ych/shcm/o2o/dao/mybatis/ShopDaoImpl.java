package com.ych.shcm.o2o.dao.mybatis;

import com.ych.core.dao.BaseSqlSessionDaoSupport;
import com.ych.core.model.PagedList;
import com.ych.shcm.o2o.dao.ShopDao;
import com.ych.shcm.o2o.model.Constants;
import com.ych.shcm.o2o.model.Shop;
import com.ych.shcm.o2o.model.ShopImage;
import com.ych.shcm.o2o.parameter.QueryShopParameter;
import org.apache.commons.lang3.ArrayUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * 店铺Dao实现
 * <p>
 * Created by U on 2017/7/7.
 */
@Lazy
@Repository("shcm.o2o.dao.mybatis.ShopDao")
public class ShopDaoImpl extends BaseSqlSessionDaoSupport implements ShopDao {

    private static final String NAMESPACE = "com.ych.shcm.o2o.dao.mybatis.ShopMapper";

    private static final String INSERT = NAMESPACE + ".insert";

    private static final String UPDATE = NAMESPACE + ".update";

    private static final String EXISTS_CONFLICT = NAMESPACE + ".existsConflict";

    private static final String SELECT_BY_ID = NAMESPACE + ".selectById";

    private static final String SELECT_BY_USERID = NAMESPACE + ".selectByUserId";

    private static final String SELECT_DETAIL_BY_ID = NAMESPACE + ".selectDetailById";

    private static final String SELECT_DETAIL_BY_NAME = NAMESPACE + ".selectDetailByName";

    private static final String SELECT_LIST = NAMESPACE + ".selectList";

    private static final String INSERT_IMAGE = NAMESPACE + ".insertImage";

    private static final String DELETE_IMAGE_BY_ID = NAMESPACE + ".deleteImageById";

    private static final String UPDATE_IMAGE = NAMESPACE + ".updateImage";

    private static final String SELECT_IMAGE_BY_SHOPID = NAMESPACE + ".selectImageByShopId";

    private static final String EXISTS_CONFLICT_IMAGE = NAMESPACE + ".existsConflictImage";

    private static final Map<String, String> SORT_COLUMNS;

    static {
        HashMap<String, String> cols = new HashMap<>();
        cols.put("name", "name");
        cols.put("distance", "distance");
        SORT_COLUMNS = Collections.unmodifiableMap(cols);
    }

    @Resource(name = Constants.CACHE_MANAGER)
    private CacheManager cacheManager;

    private Cache shopCache;

    @PostConstruct
    public void init() {
        shopCache = cacheManager.getCache(CACHE_NAME);
    }

    @Resource(name = Constants.SQL_SESSION_TEMPLATE)
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    @Override
    public int insert(Shop shop) {
        return getSqlSession().insert(INSERT, shop);
    }

    @Caching(
            evict = {
                    @CacheEvict(key = "#shop.id", condition = "#result > 0", cacheNames = CACHE_NAME, cacheManager = Constants.CACHE_MANAGER),
                    @CacheEvict(key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{'Detail', #shop.id})", condition = "#result > 0", cacheNames = CACHE_NAME, cacheManager = Constants.CACHE_MANAGER),
            }
    )
    @Override
    public int update(Shop shop) {
        return getSqlSession().update(UPDATE, shop);
    }

    @Override
    public boolean existsConflict(Shop shop) {
        return 0 < (Integer) getSqlSession().selectOne(EXISTS_CONFLICT, shop);
    }

    @Cacheable(cacheNames = CACHE_NAME, cacheManager = Constants.CACHE_MANAGER)
    @Override
    public Shop selectById(BigDecimal id) {
        return getSqlSession().selectOne(SELECT_BY_ID, id);
    }

    @Cacheable(key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{'UserShop', #id})", cacheNames = CACHE_NAME, cacheManager = Constants.CACHE_MANAGER)
    @Override
    public List<Shop> selectByUserId(BigDecimal id) {
        return getSqlSession().selectList(SELECT_BY_USERID, id);
    }

    @Cacheable(key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{'Detail', #id})", cacheNames = CACHE_NAME, cacheManager = Constants.CACHE_MANAGER)
    @Override
    public Shop selectDetailById(BigDecimal id) {
        return getSqlSession().selectOne(SELECT_DETAIL_BY_ID, id);
    }

    @Override
    public Shop selectDetailByName(String name) {
        return getSqlSession().selectOne(SELECT_DETAIL_BY_NAME, name);
    }

    @Override
    public PagedList<Shop> selectPagedList(QueryShopParameter parameter) {
        convertSortColumnName(parameter, SORT_COLUMNS);
        return selectPaged(SELECT_LIST, parameter);
    }

    @CacheEvict(key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{'Images', #image.shopId})", condition = "#result > 0", cacheNames = CACHE_NAME, cacheManager = Constants.CACHE_MANAGER)
    @Override
    public int insertImage(ShopImage image) {
        return getSqlSession().insert(INSERT_IMAGE, image);
    }

    @Override
    public int deleteImageById(ShopImage... images) {
        if (images.length == 0) {
            return 0;
        }

        ArrayList<BigDecimal> id = new ArrayList<>();
        HashSet<BigDecimal> shopIds = new HashSet<>();

        for (ShopImage image : images) {
            id.add(image.getId());
            shopIds.add(image.getShopId());
        }

        for (BigDecimal shopId : shopIds) {
            shopCache.evict(ArrayUtils.toString(new Object[]{"Images", shopId}));
        }

        return getSqlSession().delete(DELETE_IMAGE_BY_ID, Arrays.asList(id));
    }

    @CacheEvict(key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{'Images', #image.shopId})", condition = "#result > 0", cacheNames = CACHE_NAME, cacheManager = Constants.CACHE_MANAGER)
    @Override
    public int updateImage(ShopImage image) {
        return getSqlSession().update(UPDATE_IMAGE, image);
    }

    @Override
    public boolean existsConflictImages(ShopImage image) {
        return 0 < (Integer) getSqlSession().selectOne(EXISTS_CONFLICT_IMAGE, image);
    }

    @Cacheable(key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{'Images', #shopId})", cacheNames = CACHE_NAME, cacheManager = Constants.CACHE_MANAGER)
    @Override
    public List<ShopImage> selectImagesByShopId(BigDecimal shopId) {
        return getSqlSession().selectList(SELECT_IMAGE_BY_SHOPID, shopId);
    }

}
