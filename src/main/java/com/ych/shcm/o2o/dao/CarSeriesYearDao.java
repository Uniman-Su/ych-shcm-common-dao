package com.ych.shcm.o2o.dao;

import java.math.BigDecimal;
import java.util.List;

import com.ych.shcm.o2o.model.CarSeriesYear;

/**
 * 车系年份的Dao
 * 
 * @author U
 *
 */
public interface CarSeriesYearDao {

	/**
	 * 车系年份缓存名称
	 */
	String CACHE_NAME = "CarSeriesYear";

	/**
	 * 根据Id查询车型年份
	 * 
	 * @param id
	 *            ID
	 * @param lock
	 *            是否锁定数据
	 * @return 车型年份
	 */
	CarSeriesYear selectById(BigDecimal id, boolean lock);

	/**
	 * 插入车型年份
	 * 
	 * @param year
	 *            年份
	 * @return 插入的行数
	 */
	int insert(CarSeriesYear year);

	/**
	 * 批量插入车型年份
	 * 
	 * @param years
	 *            年份列表
	 * @return 插入的行数
	 */
	int insert(List<CarSeriesYear> years);

	/**
	 * 导入车型年份,导入跟添加不同的是导入认为数据已存在ID
	 * 
	 * @param years
	 *            年份列表
	 * @return 插入的行数
	 */
	int importSeriesYears(List<CarSeriesYear> years);

	/**
	 * 更新车型年份
	 * 
	 * @param year
	 *            车型年份
	 * @return 更新的行数
	 */
	int update(CarSeriesYear year);

	/**
	 * 删除车型年份
	 * 
	 * @param year
	 *            车型年份
	 * @return 删除的行数
	 */
	int delete(CarSeriesYear year);

	/**
	 * 根据车系ID查询可用的年份列表,并且按照年份升序排列.
	 * 
	 * @param seriesId
	 *            车系ID
	 * @return 车系年份列表
	 */
	List<CarSeriesYear> selectBySeriesId(BigDecimal seriesId);

}
