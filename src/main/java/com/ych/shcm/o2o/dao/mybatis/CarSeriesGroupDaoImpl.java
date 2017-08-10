package com.ych.shcm.o2o.dao.mybatis;

import com.ych.core.dao.BaseSqlSessionDaoSupport;
import com.ych.core.model.PagedList;
import com.ych.shcm.o2o.dao.CarSeriesGroupDao;
import com.ych.shcm.o2o.model.CarSeriesGroup;
import com.ych.shcm.o2o.model.CarSeriesGroupSeries;
import com.ych.shcm.o2o.model.Constants;
import com.ych.shcm.o2o.model.ServicePack;
import com.ych.shcm.o2o.parameter.QueryCarSeriesGroupParameter;
import com.ych.shcm.o2o.parameter.QueryCarSeriesGroupSeriesParameter;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * 车系组的Dao
 * <p>
 * Created by U on 2017/7/6.
 */
@Lazy
@Repository("shcm.o2o.dao.mybatis.CarSeriesGroupDao")
public class CarSeriesGroupDaoImpl extends BaseSqlSessionDaoSupport implements CarSeriesGroupDao {

    private static final String NAMESPACE = "com.ych.shcm.o2o.dao.mybatis.CarSeriesGroupMapper";

    private static final String INSERT = NAMESPACE + ".insert";

    private static final String UPDATE = NAMESPACE + ".update";

    private static final String DELETE = NAMESPACE + ".delete";

    private static final String SELECT_BY_ID = NAMESPACE + ".selectById";

    private static final String SELECT_LIST = NAMESPACE + ".selectList";

    private static final String INSERT_SERIES = NAMESPACE + ".insertSeries";

    private static final String DELETE_SERIES = NAMESPACE + ".deleteSeries";

    private static final String DELETE_SERIES_BY_ID = NAMESPACE + ".deleteSeriesById";

    private static final String SELECT_SERIES_LIST = NAMESPACE + ".selectSeriesList";

    private static final String SELECT_CAR_SERIES_GROUP_BY_SERIES_ID = NAMESPACE + ".selectCarSeriesGroupBySeriesId";

    private static final String SELECT_SERVICE_PACK_BY_GROUP_ID = NAMESPACE + ".selectServicePackByGroupId";

    @Resource(name = Constants.SQL_SESSION_TEMPLATE)
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    @Override
    public int insert(CarSeriesGroup seriesGroup) {
        return getSqlSession().insert(INSERT, seriesGroup);
    }

    @Override
    public int update(CarSeriesGroup seriesGroup) {
        return getSqlSession().update(UPDATE, seriesGroup);
    }

    @Override
    public int delete(CarSeriesGroup seriesGroup) {
        return getSqlSession().delete(DELETE, seriesGroup);
    }

    @Override
    public CarSeriesGroup selectById(BigDecimal id) {
        return getSqlSession().selectOne(SELECT_BY_ID, id);
    }

    @Override
    public PagedList<CarSeriesGroup> selectPagedList(QueryCarSeriesGroupParameter parameter) {
        return selectPaged(SELECT_LIST, parameter);
    }

    @Override
    public int insertSeries(List<CarSeriesGroupSeries> serieses) {
        return getSqlSession().insert(INSERT_SERIES, serieses);
    }

    @Override
    public int deleteSeries(CarSeriesGroupSeries series) {
        return getSqlSession().delete(DELETE_SERIES, series);
    }

    @Override
    public int deleteSeriesById(BigDecimal... id) {
        if (id.length == 0) {
            return 0;
        }

        return getSqlSession().delete(DELETE_SERIES_BY_ID, Arrays.asList(id));
    }

    @Override
    public PagedList<CarSeriesGroupSeries> selectPagedSeriesList(QueryCarSeriesGroupSeriesParameter parameter) {
        return selectPaged(SELECT_SERIES_LIST, parameter);
    }

    /**
     * 根据车系id查询车系组id
     *
     * @param seriesId
     * @return
     */
    @Override
    public CarSeriesGroup selectCarSeriesGroupBySeriesId(BigDecimal seriesId) {
        return getSqlSession().selectOne(SELECT_CAR_SERIES_GROUP_BY_SERIES_ID, seriesId);

    }

    /**
     * 根据组id查询服务包并排序
     *
     * @param groupId
     *         组id
     * @return 服务包列表
     */
    @Override
    public List<ServicePack> selectServicePackByGroupId(BigDecimal groupId) {
        return getSqlSession().selectList(SELECT_SERVICE_PACK_BY_GROUP_ID, groupId);
    }
}
