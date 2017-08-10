package com.ych.shcm.o2o.dao;

import java.math.BigDecimal;

import com.ych.shcm.o2o.model.Operator;

/**
 * 管理员的Dao
 * <p>
 * Created by U on 2017/7/11.
 */
public interface OperatorDao {

    /**
     * 插入管理员信息
     *
     * @param operator
     *         管理员信息
     * @return 插入的行数
     */
    int insert(Operator operator);

    /**
     * 更新管理员信息
     *
     * @param operator
     *         管理员信息
     * @return 修改的行数
     */
    int update(Operator operator);

    /**
     * 根据ID查找管理员信息
     *
     * @param id
     *         ID
     * @return 管理员信息
     */
    Operator selectById(BigDecimal id);

    /**
     * 根据用户名查找管理员信息
     *
     * @param username
     *         用户名
     * @return 管理员信息
     */
    Operator selectByUsername(String username);

}
