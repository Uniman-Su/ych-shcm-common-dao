package com.ych.shcm.o2o.dao;

import java.math.BigDecimal;
import java.util.List;

import com.ych.shcm.o2o.model.ServiceProvider;
import com.ych.shcm.o2o.model.ServiceProviderBusinessArea;

/**
 * 服务商Dao
 * <p>
 * Created by U on 2017/7/13.
 */
public interface ServiceProviderDao {

    /**
     * 服务商
     */
    String CACHE_NAME = "ServiceProvider";

    /**
     * 插入服务商信息
     *
     * @param serviceProvider
     *         服务商信息
     * @return 插入的行数
     */
    int insert(ServiceProvider serviceProvider);

    /**
     * 更新服务商信息
     *
     * @param serviceProvider
     *         服务商信息
     * @return 更新的行数
     */
    int update(ServiceProvider serviceProvider);

    /**
     * 根据ID查询服务商信息
     *
     * @param id
     *         ID
     * @return 服务商信息
     */
    ServiceProvider selectById(BigDecimal id);

    /**
     * 查询指定业务地区的服务商
     *
     * @param areaId
     *         地区ID
     * @return 服务商
     */
    ServiceProvider selectByBusinessAreaId(String areaId);

    /**
     * 插入服务商的业务地区
     *
     * @param businessAreas
     *         业务地区列表
     * @return 插入的行数
     */
    int insertBusinessArea(List<ServiceProviderBusinessArea> businessAreas);

    /**
     * 删除服务商的业务地区
     *
     * @param serviceProviderId
     *         服务商ID
     * @return 删除的行数
     */
    int deleteBusinessArea(BigDecimal serviceProviderId);

    /**
     * 删除服务商的业务地区
     *
     * @param serviceProviderId
     *         服务商ID
     * @param areaIds
     *         地区ID
     * @return 删除的行数
     */
    int deleteBusinessArea(BigDecimal serviceProviderId, List<String> areaIds);

}
