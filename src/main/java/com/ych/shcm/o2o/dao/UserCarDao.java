package com.ych.shcm.o2o.dao;

import com.ych.core.model.PagedList;
import com.ych.shcm.o2o.model.CarUserHistory;
import com.ych.shcm.o2o.model.UserCar;
import com.ych.shcm.o2o.parameter.QueryCarUserHistoryParameter;
import com.ych.shcm.o2o.parameter.QueryUserCarParameter;

import java.math.BigDecimal;

/**
 * 用户和车辆关系的Dao
 * <p>
 * Created by U on 2017/7/10.
 */
public interface UserCarDao {

    /**
     * 插入用户车辆
     *
     * @param userCar
     *         用户车辆
     * @return 插入的行数
     */
    int insertUserCar(UserCar userCar);

    /**
     * 根据ID删除数据
     *
     * @param id
     *         ID
     * @return 删除的行数
     */
    int deleteUserCarById(BigDecimal id);

    /**
     * 根据ID查询用户车辆信息
     *
     * @param id
     *         UD
     * @return 用户车辆信息
     */
    UserCar selectUserCarById(BigDecimal id);

    /**
     * 根据用户ID查询一个用户车辆信息
     *
     * @param userId
     *         用户ID
     * @return 用户车辆信息
     */
    UserCar selectOneByUserId(BigDecimal userId);

    /**
     * 根据用户ID和车辆ID查询用户车辆信息
     *
     * @param userId
     *         用户ID
     * @param carId
     *         车辆ID
     * @return 用户车辆信息
     */
    UserCar selectUserCarByUserIdAndCarId(BigDecimal userId, BigDecimal carId);

    /**
     * 查询分页的用户车辆列表
     *
     * @param parameter
     *         查询参数
     * @return 用户车辆
     */
    PagedList<UserCar> selectPagedUserCarList(QueryUserCarParameter parameter);

    /**
     * 插入车辆的用户变更历史
     *
     * @param userHistory
     *         用户变更历史
     * @return 插入的行数
     */
    int insertUserHistory(CarUserHistory userHistory);

    /**
     * 查询用户车辆历史信息
     *
     * @param parameter
     *         查询参数
     * @return 分页列表
     */
    PagedList<CarUserHistory> selectPagedUserHistoryList(QueryCarUserHistoryParameter parameter);

    /**
     * 通过车辆id查询车辆和用户关联
     * @param id
     * @return
     */
    UserCar selectUserCarByCarId(BigDecimal id);
}
