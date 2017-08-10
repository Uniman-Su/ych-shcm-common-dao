package com.ych.shcm.o2o.dao;

import java.math.BigDecimal;

import com.ych.shcm.o2o.model.UserWechatInfo;

/**
 * 用户微信信息Dao
 * <p>
 * Created by U on 2017/7/13.
 */
public interface UserWechatInfoDao {

    /**
     * 插入用户微信信息
     *
     * @param userWechatInfo
     *         用户微信信息
     * @return 插入的行数
     */
    int insert(UserWechatInfo userWechatInfo);

    /**
     * 更新用户微信信息
     *
     * @param userWechatInfo
     *         用户微信信息
     * @return 更新的行数
     */
    int update(UserWechatInfo userWechatInfo);

    /**
     * 根据ID查找用户微信信息
     *
     * @param id
     *         ID
     * @return 用户微信信息
     */
    UserWechatInfo selectById(BigDecimal id);

    /**
     * 根据用户ID查找用户微信信息
     *
     * @param userId
     *         用户ID
     * @return 用户微信信息
     */
    UserWechatInfo selectByUserId(BigDecimal userId);

    /**
     * 根据OpenID查找用户微信信息
     *
     * @param openId
     *         OpenID
     * @return 用户微信信息
     */
    UserWechatInfo selectByOpenId(String openId);

}
