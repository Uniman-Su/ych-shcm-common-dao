package com.ych.shcm.o2o.dao;

import java.math.BigDecimal;
import java.util.List;

import com.ych.shcm.o2o.model.User;
import com.ych.shcm.o2o.model.UserAccessChannel;

/**
 * 用户的Dao
 * <p>
 * Created by U on 2017/7/10.
 */
public interface UserDao {

    /**
     * 缓存名称
     */
    String CACHE_NAME = "User";

    /**
     * 插入用户数据
     *
     * @param user
     *         用户数据
     * @return 插入的行数
     */
    int insert(User user);

    /**
     * 更新用户数据
     *
     * @param user
     *         用户数据
     * @return 更新的行数
     */
    int update(User user);

    /**
     * 查询是否存在冲突数据,现在来说就是指电话号码
     *
     * @param user
     *         用户数据
     * @return 存在冲突则返回true
     */
    boolean existsConflict(User user);

    /**
     * 根据ID获取用户信息
     *
     * @param id
     *         ID
     * @return 用户信息
     */
    User selectById(BigDecimal id);

    /**
     * 根据电话号码获取用户信息
     *
     * @param phone
     *         电话号码
     * @return 用户信息
     */
    User selectByPhone(String phone);

    /**
     * 根据用户名查找用户信息
     *
     * @param userName
     *         用户名
     * @return 用户信息
     */
    User selectByUserName(String userName);

    /**
     * 插入用户的访问渠道信息
     *
     * @param accessChannel
     *         访问渠道
     * @return 插入的行数
     */
    int insertAccessChannel(UserAccessChannel accessChannel);

    /**
     * 更新用户的访问渠道信息
     *
     * @param accessChannel
     *         访问渠道
     * @return 更新的行数
     */
    int updateAccessChannel(UserAccessChannel accessChannel);

    /**
     * 查询渠道信息
     *
     * @param userId
     *         用户ID
     * @param channelId
     *         渠道ID
     * @param userChannelId
     *         用户在渠道的ID
     * @return 渠道信息
     */
    UserAccessChannel selectAccessChannelByUserChannelId(BigDecimal userId, BigDecimal channelId, String userChannelId);

    /**
     * 查询渠道信息
     *
     * @param channelId
     *         渠道ID
     * @param userChannelId
     *         用户在渠道的ID
     * @return 渠道信息
     */
    UserAccessChannel selectAccessChannelByChannelId(BigDecimal channelId, String userChannelId);

    /**
     * 查询渠道信息
     *
     * @param userId
     *         用户ID
     * @param channelId
     *         渠道ID
     * @return 渠道信息
     */
    UserAccessChannel selectAccessChannelByUserChannel(BigDecimal userId, BigDecimal channelId);

    /**
     * 查询渠道信息列表
     *
     * @param userId
     *         用户ID
     * @return 渠道信息列表
     */
    List<UserAccessChannel> selectAccessChannelUserChannelList(BigDecimal userId);

}
