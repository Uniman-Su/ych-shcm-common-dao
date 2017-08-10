package com.ych.shcm.o2o.dao;

import com.ych.core.model.PagedList;
import com.ych.shcm.o2o.model.CarSeriesGroup;
import com.ych.shcm.o2o.model.CarSeriesGroupSeries;
import com.ych.shcm.o2o.model.ServicePack;
import com.ych.shcm.o2o.parameter.QueryCarSeriesGroupParameter;
import com.ych.shcm.o2o.parameter.QueryCarSeriesGroupSeriesParameter;

import java.math.BigDecimal;
import java.util.List;

/**
 * 车型组的数据访问类
 * <p>
 * Created by U on 2017/7/6.
 */
public interface CarSeriesGroupDao {

    /**
     * 插入车系组
     *
     * @param seriesGroup
     *         车系组
     * @return 插入行数
     */
    int insert(CarSeriesGroup seriesGroup);

    /**
     * 更新车系组
     *
     * @param seriesGroup
     *         车系组
     * @return 更新的行数
     */
    int update(CarSeriesGroup seriesGroup);

    /**
     * 删除车系组
     *
     * @param seriesGroup
     *         车系组
     * @return 删除的行数
     */
    int delete(CarSeriesGroup seriesGroup);

    /**
     * 根据ID查询车系组信息
     *
     * @param id
     *         ID
     * @return 车系组信息
     */
    CarSeriesGroup selectById(BigDecimal id);

    /**
     * 查询车系组分页列表
     *
     * @param parameter
     *         查询参数
     * @return 分页列表
     */
    PagedList<CarSeriesGroup> selectPagedList(QueryCarSeriesGroupParameter parameter);

    /**
     * 插入车系
     *
     * @param serieses
     *         车系列表
     * @return 插入的行数
     */
    int insertSeries(List<CarSeriesGroupSeries> serieses);

    /**
     * 删除车系
     *
     * @param series
     *         车系
     * @return 删除的行数
     */
    int deleteSeries(CarSeriesGroupSeries series);

    /**
     * 根据ID删除车系
     *
     * @param id
     *         ID列表
     * @return 删除的行数
     */
    int deleteSeriesById(BigDecimal... id);

    /**
     * 查询车系的分页列表
     *
     * @param parameter
     *         查询参数
     * @return 分页列表
     */
    PagedList<CarSeriesGroupSeries> selectPagedSeriesList(QueryCarSeriesGroupSeriesParameter parameter);

    /**
     * 根据车系id查询车系组id
     *
     * @param seriesId
     *         车系ID
     * @return 车系组
     */
    CarSeriesGroup selectCarSeriesGroupBySeriesId(BigDecimal seriesId);

    /**
     * 根据组id查询服务包并排序
     *
     * @param groupId
     *         组id
     * @return 服务包列表
     */
    List<ServicePack> selectServicePackByGroupId(BigDecimal groupId);

}
