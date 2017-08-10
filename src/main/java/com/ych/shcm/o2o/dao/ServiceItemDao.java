package com.ych.shcm.o2o.dao;

import com.ych.core.model.PagedList;
import com.ych.shcm.o2o.model.ServiceItem;
import com.ych.shcm.o2o.parameter.QueryServiceItemListParameter;

import java.math.BigDecimal;
import java.util.List;

/**
 * 服务项目dao
 *
 * @author U
 */
public interface ServiceItemDao {

    /**
     * 根据ID查询服务项目
     *
     * @param id
     *         服务项目ID
     * @return 服务项目
     */
    ServiceItem selectById(BigDecimal id);

    /**
     * 更新服务项目数据
     *
     * @param serviceItem
     *         服务项目数据
     * @return 修改影响的行数
     */
    int update(ServiceItem serviceItem);

    /**
     * 插入服务项目
     *
     * @param serviceItem
     *         服务项目数据
     * @return 插入的行数
     */
    int insert(ServiceItem serviceItem);

    /**
     * 删除服务项目
     *
     * @param id
     *         服务项目id
     * @return 删除的
     */
    int delete(BigDecimal id);

    /**
     * 查询服务项目列表
     *
     * @param parameter
     *         查询参数
     * @return 查询结果
     */
    PagedList<ServiceItem> selectServiceItemList(QueryServiceItemListParameter parameter);

    /**
     * 查询服务包下的关联项目
     * @param id 服务包id
     * @return 项目列表
     */
    List<ServiceItem> selectServiceItemsOfPack(BigDecimal id);
}
