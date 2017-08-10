package com.ych.shcm.o2o.dao;

import com.ych.core.model.PagedList;
import com.ych.shcm.o2o.parameter.QueryServicePackListParameter;
import com.ych.shcm.o2o.model.ServicePack;

import java.math.BigDecimal;

/**
 * 服务包dao
 *
 * @author U
 */
public interface ServicePackDao {

    /**
     * 根据ID查询服务包
     *
     * @param id
     *         服务包ID
     * @return 服务包
     */
    ServicePack selectById(BigDecimal id);

    /**
     * 更新服务包数据
     *
     * @param servicePack
     *         服务包数据
     * @return 修改影响的行数
     */
    int update(ServicePack servicePack);

    /**
     * 插入服务包
     *
     * @param servicePack
     *         服务包数据
     * @return 插入的行数
     */
    int insert(ServicePack servicePack);


    /**
     * 删除服务包
     * @param id 服务包id
     * @return 删除的
     */
    int delete(BigDecimal id);



    /**
     * 查询服务包列表
     *
     * @param parameter 查询参数
     * @return 查询结果
     */
    PagedList<ServicePack> selectServicePackList(QueryServicePackListParameter parameter);

}
