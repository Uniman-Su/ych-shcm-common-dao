package com.ych.shcm.o2o.dao;

import com.ych.core.model.PagedList;
import com.ych.shcm.o2o.model.Car;
import com.ych.shcm.o2o.model.CarExpiredMaintenanceInfo;
import com.ych.shcm.o2o.model.CarModel;
import com.ych.shcm.o2o.parameter.QueryCarExpiredMaintenanceParameter;
import com.ych.shcm.o2o.parameter.QueryCarParameter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 车辆的Dao
 * <p>
 * Created by U on 2017/7/10.
 */
public interface CarDao {

    /**
     * 车辆的缓存
     */
    String CACHE_NAME = "Car";

    /**
     * 插入车辆信息
     *
     * @param car
     *         车辆信息
     * @return 插入的行数
     */
    int insert(Car car);

    /**
     * 更新车辆信息
     *
     * @param car
     *         车辆信息
     * @return 更新的行数
     */
    int update(Car car);

    /**
     * 根据ID查询车辆信息
     *
     * @param id
     *         车辆ID
     * @return 车辆信息
     */
    Car selectById(BigDecimal id);

    /**
     * 根据VIN码查询车辆信息
     *
     * @param vin
     *         VIN码
     * @return 车辆信息
     */
    Car selectByVin(String vin);

    /**
     * 查询车辆列表
     *
     * @param parameter
     *         查询参数
     * @return 分页数据
     */
    PagedList<Car> selectPagedList(QueryCarParameter parameter);

    /**
     * 插入车辆维护信息
     *
     * @param expiredMaintenanceInfo
     *         车辆维护信息
     * @return 插入的行数
     */
    int insertExpiredMaintenanceInfo(CarExpiredMaintenanceInfo expiredMaintenanceInfo);

    /**
     * 根据ID查询车辆过期维护信息
     *
     * @param id
     *         ID
     * @return 车辆过期维护信息
     */
    CarExpiredMaintenanceInfo selectExpiredMaintenanceInfoById(BigDecimal id);

    /**
     * 根据车辆ID和生效时间车辆过期维护信息
     *
     * @param carId
     *         车辆ID
     * @param effectTime
     *         生效时间
     * @return 车辆过期维护信息
     */
    CarExpiredMaintenanceInfo selectExpiredMaintenanceInfoByCarIdAndEffectTime(BigDecimal carId, Date effectTime);

    /**
     * 查询车辆过期维护信息
     *
     * @param parameter
     *         查询参数
     * @return 分页数据
     */
    PagedList<QueryCarExpiredMaintenanceParameter> selectPagedExpiredMaintenanceInfoList(QueryCarExpiredMaintenanceParameter parameter);

    /**
     * 查询用户车辆列表
     *
     * @param userId
     *         用户ID
     * @return 用户车辆列表
     */
    List<CarModel> selectCarsOfUser(BigDecimal userId);
}
