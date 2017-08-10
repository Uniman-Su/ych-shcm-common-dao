package com.ych.shcm.o2o.dao;

import java.util.List;

import com.ych.shcm.o2o.model.Area;

/**
 * 地区Dao
 * <p>
 * Created by U on 2017/7/4.
 */
public interface AreaDao {

    /**
     * 缓存名称
     */
    String CACHE_NAME = "Area";

    /**
     * 根据地区ID查询地区信息
     *
     * @param id
     *         地区ID
     * @return 地区信息
     */
    Area selectById(String id);

    /**
     * 根据父节点查询地区列表
     *
     * @param parentId
     *         父节点ID
     * @return 制定地区的子节点列表
     */
    List<Area> selectByParentId(String parentId);

    /**
     * 查询从最顶层祖先到指定节点的列表
     *
     * @param id
     *         地区ID
     * @return 最顶层祖先到指定节点的列表
     */
    List<Area> selectFromAncestors(String id);

    /**
     * 查询从最顶层祖先到指定节点的ID列表
     *
     * @param id
     *         地区ID
     * @return 最顶层祖先到指定节点的ID列表
     */
    List<String> selectFromAncestorIds(String id);

    /**
     * 查询指定节点并包含其所有的子孙节点
     *
     * @param id
     *         地区ID
     * @return 指定节点并包含其所有的子孙节点
     */
    List<Area> selectToDescendants(String id);

    /**
     * 查询指定节点并包含其所有的子孙节点的ID
     *
     * @param id
     *         地区ID
     * @return 指定节点并包含其所有的子孙节点的ID
     */
    List<String> selectToDescendantIds(String id);

}
