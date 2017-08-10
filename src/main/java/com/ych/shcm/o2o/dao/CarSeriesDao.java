package com.ych.shcm.o2o.dao;

import java.math.BigDecimal;
import java.util.List;

import com.ych.core.model.PagedList;
import com.ych.shcm.o2o.model.CarSeries;
import com.ych.shcm.o2o.parameter.QueryCarSeriesListParameter;

/**
 * 车系的Dao
 * 
 * @author U
 *
 */
public interface CarSeriesDao {

	/**
	 * 车系缓存的名称
	 */
	String CACHE_NAME = "CarSeries";

	/**
	 * 根据ID查询车系
	 * 
	 * @param id
	 *            ID
	 * @param lock
	 *            是否锁定数据
	 * @return 车系
	 */
	CarSeries selectById(BigDecimal id, boolean lock);

	/**
	 * 插入车系
	 * 
	 * @param series
	 *            车系
	 * @return 插入的行数
	 */
	int insert(CarSeries series);

	/**
	 * 批量插入车系
	 * 
	 * @param serieses
	 *            车系列表
	 * @return 插入的行数
	 */
	int insert(List<CarSeries> serieses);

	/**
	 * 导入车系,导入跟添加不同的是导入认为数据已存在ID
	 * 
	 * @param serieses
	 *            车系列表
	 * @return 插入的行数
	 */
	int importSerieses(List<CarSeries> serieses);

	/**
	 * 更新车系
	 * 
	 * @param series
	 *            车系
	 * @return 更新的行数
	 */
	int update(CarSeries series);

	/**
	 * 删除车系
	 * 
	 * @param series
	 *            车系
	 * @return 删除的行数
	 */
	int delete(CarSeries series);

	/**
	 * 根据品牌和制造厂查询可用的车系列表,两个参数不能都为null.<br>
	 * 车系列表按照首字母排序
	 * 
	 * @param brandId
	 *            品牌ID
	 * @param factoryId
	 *            制造厂ID
	 * @return 车系列表
	 */
	List<CarSeries> selectList(BigDecimal brandId, BigDecimal factoryId);

	/**
	 * 分页查询车系
	 * 
	 * @param parameter
	 *            查询参数
	 * @return 分页列表数据
	 */
	PagedList<CarSeries> selectPagedList(QueryCarSeriesListParameter parameter);

}
