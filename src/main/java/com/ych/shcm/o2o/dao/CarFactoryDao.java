package com.ych.shcm.o2o.dao;

import java.math.BigDecimal;
import java.util.List;

import com.ych.core.model.PagedList;
import com.ych.shcm.o2o.model.CarBrandFactory;
import com.ych.shcm.o2o.model.CarFactory;
import com.ych.shcm.o2o.parameter.QueryCarFactoryListParameter;

/**
 * 车型制造厂家的Dao
 * 
 * @author U
 *
 */
public interface CarFactoryDao {

	/**
	 * 车型制造厂家的缓存名称
	 */
	String CACHE_NAME = "CarFactory";

	/**
	 * 根据ID获取车型制造厂家
	 * 
	 * @param id
	 *            ID
	 * @param lock
	 *            是否锁定数据
	 * @return 车型制造厂家
	 */
	CarFactory selectById(BigDecimal id, boolean lock);

	/**
	 * 插入车型制造厂家
	 * 
	 * @param factory
	 *            车型制造厂家
	 * @return 插入的行
	 */
	int insert(CarFactory factory);

	/**
	 * 批量插入车型制造厂家
	 * 
	 * @param factories
	 *            车型制造厂家列表
	 * @return 插入的行
	 */
	int insert(List<CarFactory> factories);

	/**
	 * 导入车型制造厂家,导入与插入不同的是导入认为数据已经存在ID
	 * 
	 * @param factories
	 *            车型制造厂家列表
	 * @return 插入的行
	 */
	int importFactories(List<CarFactory> factories);

	/**
	 * 更新车型制造厂家
	 * 
	 * @param factory
	 *            车型制造厂家
	 * @return 更新的行数
	 */
	int update(CarFactory factory);

	/**
	 * 删除车型制造厂家
	 * 
	 * @param factory
	 *            车型制造厂家
	 * @return 更新的行数
	 */
	int delete(CarFactory factory);

	/**
	 * 根据品牌ID获取可用的车型制造厂家,并根据首字母排序
	 * 
	 * @param brandId
	 *            品牌ID
	 * @return 车型制造厂家列表
	 */
	List<CarFactory> selectByBrandId(BigDecimal brandId);

	/**
	 * 查询车型制造厂家的分页列表
	 * 
	 * @param parameter
	 *            查询参数
	 * @return 车型制造厂家分页列表
	 */
	PagedList<CarFactory> selectPagedList(QueryCarFactoryListParameter parameter);

	/**
	 * 插入品牌对工厂的关系
	 * 
	 * @param factory
	 *            品牌工厂映射
	 * @return 插入的行数
	 */
	int insertBrandFactory(CarBrandFactory factory);

	/**
	 * 插入品牌对工厂的关系
	 * 
	 * @param factories
	 *            品牌工厂映射列表
	 * @return 插入的行数
	 */
	int insertBrandFactories(List<CarBrandFactory> factories);

	/**
	 * 删除品牌对工厂得关系
	 * 
	 * @param factory
	 *            品牌工厂映射
	 * @return 删除的行数
	 */
	int delete(CarBrandFactory factory);

}
