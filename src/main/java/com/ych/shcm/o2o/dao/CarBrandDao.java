package com.ych.shcm.o2o.dao;

import java.math.BigDecimal;
import java.util.List;

import com.ych.core.model.PagedList;
import com.ych.shcm.o2o.model.CarBrand;
import com.ych.shcm.o2o.parameter.QueryCarBrandListParameter;

/**
 * 车型品牌Dao
 * 
 * @author U
 *
 */
public interface CarBrandDao {

	/**
	 * 车型品牌的缓存名称
	 */
	String CACHE_NAME = "CarBrand";

	/**
	 * 根据ID获取车型品牌
	 * 
	 * @param id
	 *            ID
	 * @param lock
	 *            是否锁定行数据
	 * @return 车型品牌
	 */
	CarBrand selectById(BigDecimal id, boolean lock);

	/**
	 * 插入车型品牌
	 * 
	 * @param brand
	 *            车型品牌
	 * @return 插入行数
	 */
	int insert(CarBrand brand);

	/**
	 * 批量插入车型品牌
	 * 
	 * @param brands
	 *            车型品牌列表
	 * @return 插入的行数
	 */
	int insert(List<CarBrand> brands);

	/**
	 * 导入车型品牌,导入跟添加不同的是导入认为数据已存在ID
	 * 
	 * @param brands
	 *            车型品牌列表
	 * @return 插入的行数
	 */
	int importBrands(List<CarBrand> brands);

	/**
	 * 更新车型品牌
	 * 
	 * @param brand
	 *            车型品牌
	 * @return 更新行数
	 */
	int update(CarBrand brand);

	/**
	 * 删除车型品牌
	 * 
	 * @param brand
	 *            车型品牌
	 * @return 更新的行数
	 */
	int delete(CarBrand brand);

	/**
	 * 查询所有可用的车型品牌列表,并按照首字母排序
	 * 
	 * @return 车型品牌列表
	 */
	List<CarBrand> selectList();

	/**
	 * 查询车型的分页列表
	 * 
	 * @param parameter
	 *            查询参数
	 * @return 分页列表
	 */
	PagedList<CarBrand> selectPagedList(QueryCarBrandListParameter parameter);

}
