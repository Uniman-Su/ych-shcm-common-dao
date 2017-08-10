package com.ych.shcm.o2o.dao;

import java.math.BigDecimal;

import com.ych.shcm.o2o.model.AccessChannel;

/**
 * 访问渠道Dao
 * <p>
 * Created by U on 2017/7/5.
 */
public interface AccessChannelDao {

    /**
     * 缓存名称
     */
    String CACHE_NAME = "AccessChannel";

    /**
     * 根据ID查找访问渠道信息
     *
     * @param id
     *         ID
     * @return 访问渠道信息
     */
    AccessChannel selectById(BigDecimal id);

    /**
     * 根据编码查找访问渠道信息
     *
     * @param code
     *         编码
     * @return 访问渠道信息
     */
    AccessChannel selectByCode(String code);

}
