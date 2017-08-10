package com.ych.shcm.o2o.dao.mybatis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.ych.core.dao.BaseSqlSessionDaoSupport;
import com.ych.shcm.o2o.dao.AreaDao;
import com.ych.shcm.o2o.model.Area;
import com.ych.shcm.o2o.model.Constants;

/**
 * 地区Dao实现
 * <p>
 * Created by U on 2017/7/4.
 */
@Lazy
@Repository("shcm.o2o.dao.mybatis.AreaDao")
public class AreaDaoImpl extends BaseSqlSessionDaoSupport implements AreaDao {

    private static final String NAMESPACE = "com.ych.shcm.o2o.dao.mybatis.AreaDaoMapper";

    private static final String SELECT_BY_ID = NAMESPACE + ".selectById";

    private static final String SELECT_BY_PARENTID = NAMESPACE + ".selectByParentId";

    private static final String SELECT_ANCESTORS = NAMESPACE + ".selectAncestors";

    private static final String SELECT_ANCESTORIDS = NAMESPACE + ".selectAncestorIds";

    private static final String SELECT_DESCENDANTS = NAMESPACE + ".selectDescendants";

    private static final String SELECT_DESCENDANTIDS = NAMESPACE + ".selectDescendantIds";

    @Resource(name = Constants.SQL_SESSION_TEMPLATE)
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    @Cacheable(cacheNames = CACHE_NAME, cacheManager = Constants.CACHE_MANAGER)
    public Area selectById(String id) {
        return getSqlSession().selectOne(SELECT_BY_ID, id);
    }

    @Cacheable(key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{#parentId, 'children'})", cacheNames = CACHE_NAME, cacheManager = Constants.CACHE_MANAGER)
    public List<Area> selectByParentId(String parentId) {
        return getSqlSession().selectList(SELECT_BY_PARENTID, parentId);
    }

    @Cacheable(key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{#id, 'fromAncestors'})", cacheNames = CACHE_NAME, cacheManager = Constants.CACHE_MANAGER)
    public List<Area> selectFromAncestors(String id) {
        Area area = selectById(id);

        if (area == null) {
            return Collections.emptyList();
        }

        ArrayList<Area> ret = new ArrayList<Area>();
        HashMap<String, Integer> parameter = new HashMap<String, Integer>();
        parameter.put("left", area.getLeft());
        parameter.put("right", area.getRight());


        List<Area> ancestors = getSqlSession().selectList(SELECT_ANCESTORS, parameter);

        ret.addAll(ancestors);
        ret.add(area);

        return ret;
    }

    @Cacheable(key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{#id, 'fromAncestorsIds'})", cacheNames = CACHE_NAME, cacheManager = Constants.CACHE_MANAGER)
    public List<String> selectFromAncestorIds(String id) {
        Area area = selectById(id);

        if (area == null) {
            return Collections.emptyList();
        }

        ArrayList<String> ret = new ArrayList<String>();
        HashMap<String, Integer> parameter = new HashMap<String, Integer>();
        parameter.put("left", area.getLeft());
        parameter.put("right", area.getRight());


        List<String> ancestorIds = getSqlSession().selectList(SELECT_ANCESTORIDS, parameter);

        ret.addAll(ancestorIds);
        ret.add(area.getId());

        return ret;
    }

    @Cacheable(key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{#id, 'toDescendants'})", cacheNames = CACHE_NAME, cacheManager = Constants.CACHE_MANAGER)
    public List<Area> selectToDescendants(String id) {
        Area area = selectById(id);

        if (area == null) {
            return Collections.emptyList();
        }

        ArrayList<Area> ret = new ArrayList<Area>();
        HashMap<String, Integer> parameter = new HashMap<String, Integer>();
        parameter.put("left", area.getLeft());
        parameter.put("right", area.getRight());


        List<Area> descendants = getSqlSession().selectList(SELECT_DESCENDANTS, parameter);

        ret.add(area);
        ret.addAll(descendants);

        return ret;
    }

    @Cacheable(key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{#id, 'toDescendantIds'})", cacheNames = CACHE_NAME, cacheManager = Constants.CACHE_MANAGER)
    public List<String> selectToDescendantIds(String id) {
        Area area = selectById(id);

        if (area == null) {
            return Collections.emptyList();
        }

        ArrayList<String> ret = new ArrayList<String>();
        HashMap<String, Integer> parameter = new HashMap<String, Integer>();
        parameter.put("left", area.getLeft());
        parameter.put("right", area.getRight());


        List<String> descendantsIds = getSqlSession().selectList(SELECT_DESCENDANTIDS, parameter);

        ret.add(area.getId());
        ret.addAll(descendantsIds);

        return ret;
    }
}
