package com.ych.shcm.o2o.dao;

import java.math.BigDecimal;
import java.util.List;

import com.ych.shcm.o2o.model.CarBrand;
import com.ych.shcm.o2o.model.ShopCarBrand;

/**
 * 车型品牌的Dao
 * <p>
 * Created by U on 2017/7/7.
 */
public interface ShopCarBrandDao {

    /**
     * 插入店铺车型品牌
     *
     * @param carBrands
     *         店铺车型品牌列表
     * @return 插入的行数
     */
    int insert(List<ShopCarBrand> carBrands);

    /**
     * 删除车型品牌信息
     *
     * @param id
     *         ID列表
     * @return 删除的行数
     */
    int delete(BigDecimal... id);

    /**
     * 根据店铺ID删除车型品牌
     *
     * @param shopId
     *         店铺ID
     * @return 删除的行数
     */
    int deleteByShopId(BigDecimal shopId);

    /**
     * 根据店铺ID查询品牌列表
     *
     * @param shopId
     *         店铺ID
     * @return 品牌列表
     */
    List<CarBrand> selectByShopId(BigDecimal shopId);

}
