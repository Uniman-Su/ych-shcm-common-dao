package com.ych.shcm.o2o.dao;

import com.ych.shcm.o2o.model.CarModel;

import java.math.BigDecimal;
import java.util.List;

/**
 * 车型Dao
 * 
 * @author U
 *
 */
public interface CarModelDao {

	/**
	 * 车型缓存名称
	 */
	String CAR_MODEL_CACHE_NAME = "CarModel";

	/**
	 * 根据Id获取车型信息
	 * 
	 * @param id
	 *            ID
	 * @param lock
	 *            是否锁定
	 * @return 车型信息
	 */
	CarModel selectById(BigDecimal id, boolean lock);

	/**
	 * 插入车型信息
	 * 
	 * @param model
	 *            车型信息
	 * @return 插入的行数
	 */
	int insert(CarModel model);

	/**
	 * 批量插入车型新
	 * 
	 * @param models
	 *            车型信息列表
	 * @return 插入的行数
	 */
	int insert(List<CarModel> models);

	/**
	 * 导入车型,导入跟添加不同的是导入认为数据已存在ID
	 * 
	 * @param models
	 *            车型列表
	 * @return 插入的行数
	 */
	int importModels(List<CarModel> models);

	/**
	 * 更新车型信息
	 * 
	 * @param model
	 *            车型信息
	 * @return 更新的行数
	 */
	int update(CarModel model);

	/**
	 * 删除车型信息
	 * 
	 * @param model
	 *            车型信息
	 * @return 删除的行数
	 */
	int delete(CarModel model);

	/**
	 * 根据车系ID和年份查询可用的车型列表并按照车型排序排列
	 * 
	 * @param seriesId
	 *            车系ID
	 * @param year
	 *            年份
	 * @param lock
	 *            是否锁定数据
	 * @return 车型列表
	 */
	List<CarModel> selectBySeriesYear(BigDecimal seriesId, int year, boolean lock);


}
