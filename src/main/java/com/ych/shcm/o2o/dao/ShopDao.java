package com.ych.shcm.o2o.dao;

import java.math.BigDecimal;
import java.util.List;

import com.ych.core.model.PagedList;
import com.ych.shcm.o2o.model.Shop;
import com.ych.shcm.o2o.model.ShopImage;
import com.ych.shcm.o2o.parameter.QueryShopParameter;

/**
 * 店铺Dao
 * <p>
 * Created by U on 2017/7/6.
 */
public interface ShopDao {

    /**
     * 店铺
     */
    String CACHE_NAME = "Shop";

    /**
     * 插入店铺
     *
     * @param shop
     *         店铺
     * @return 插入行
     */
    int insert(Shop shop);

    /**
     * 修改店铺信息
     *
     * @param shop
     *         店铺信息
     * @return 更新的行
     */
    int update(Shop shop);

    /**
     * 是否存在同名店铺
     *
     * @param shop
     *         店铺信息
     * @return 存在同名返回true
     */
    boolean existsConflict(Shop shop);

    /**
     * 根据ID查询店铺信息,不包含描述字段
     *
     * @param id
     *         ID
     * @return 店铺信息
     */
    Shop selectById(BigDecimal id);

    /**
     * 根据用户ID查询用户绑定的店铺信息
     *
     * @param id
     *         用户ID
     * @return 绑定的店铺信息列表
     */
    List<Shop> selectByUserId(BigDecimal id);

    /**
     * 根据ID查询店铺信息,包含描述字段
     *
     * @param id
     *         ID
     * @return 店铺信息
     */
    Shop selectDetailById(BigDecimal id);

    /**
     * 根据名称查询店铺信息,包含描述字段
     *
     * @param name
     *         店铺名称
     * @return 店铺信息
     */
    Shop selectDetailByName(String name);

    /**
     * 查询店铺的分页列表
     *
     * @param parameter
     *         查询参数
     * @return 返回的列表
     */
    PagedList<Shop> selectPagedList(QueryShopParameter parameter);

    /**
     * 插入图片
     *
     * @param image
     *         图片
     * @return 插入的行数
     */
    int insertImage(ShopImage image);

    /**
     * 根据ID删除图片记录
     *
     * @param images
     *         要删除的图片
     * @return 删除的行数
     */
    int deleteImageById(ShopImage... images);

    /**
     * 修改图片信息
     *
     * @param image
     *         图片
     * @return 修改的行数
     */
    int updateImage(ShopImage image);

    /**
     * 查询是否存在冲突的图片数据
     *
     * @param image
     *         图片信息
     * @return 如有则返回true
     */
    boolean existsConflictImages(ShopImage image);

    /**
     * 根据店铺ID查询图片列表
     *
     * @param shopId
     *         店铺ID
     * @return 图片列表
     */
    List<ShopImage> selectImagesByShopId(BigDecimal shopId);

}
