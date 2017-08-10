package com.ych.shcm.o2o.dao;

import com.ych.shcm.o2o.model.ServiceItem;
import com.ych.shcm.o2o.model.ServicePackItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * 服务包项目dao
 *
 * @author U
 */
public interface ServicePackItemDao {

    /**
     * 根据ID查询服务包项目
     *
     * @param id
     *         服务包项目ID
     * @return 服务包项目
     */
    ServicePackItem selectById(BigDecimal id);

    /**
     * 查询服务包下关联的项目
     *
     * @param packId
     *         服务包id
     * @return 关联的项目
     */
    List<ServiceItem> selectServiceItemsByPackId(BigDecimal packId);

    /**
     * 更新服务包项目数据
     *
     * @param servicePackItem
     *         服务包项目数据
     * @return 修改影响的行数
     */
    int update(ServicePackItem servicePackItem);

    /**
     * 插入服务包项目
     *
     * @param servicePackItem
     *         服务包项目数据
     * @return 插入的行数
     */
    int insert(ServicePackItem servicePackItem);

    /**
     * 批量插入服务包项目
     *
     * @param packId
     *         服务包ID
     * @param items
     *         项目列表
     * @return 插入的行数
     */
    int insertList(BigDecimal packId, Set<BigDecimal> items);

    /**
     * 删除服务包项目
     *
     * @param id
     *         服务包项目id
     * @return 删除的
     */
    int delete(BigDecimal id);

}
