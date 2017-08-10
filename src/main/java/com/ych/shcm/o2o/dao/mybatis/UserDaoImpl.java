package com.ych.shcm.o2o.dao.mybatis;

import com.ych.core.dao.BaseSqlSessionDaoSupport;
import com.ych.shcm.o2o.dao.UserDao;
import com.ych.shcm.o2o.model.Constants;
import com.ych.shcm.o2o.model.User;
import com.ych.shcm.o2o.model.UserAccessChannel;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * 用户Dao的实现
 * <p>
 * Created by U on 2017/7/10.
 */
@Lazy
@Repository("shcm.o2o.dao.mybatis.UserDao")
public class UserDaoImpl extends BaseSqlSessionDaoSupport implements UserDao {

    private static final String NAMESPACE = "com.ych.shcm.o2o.dao.mybatis.UserMapper";

    private static final String INSERT = NAMESPACE + ".insert";

    private static final String UPDATE = NAMESPACE + ".update";

    private static final String EXISTS_CONFLICT = NAMESPACE + ".existsConflict";

    private static final String SELECT_BY_ID = NAMESPACE + ".selectById";

    private static final String SELECT_BY_PHONE = NAMESPACE + ".selectByPhone";

    private static final String SELECT_BY_USERNAME = NAMESPACE + ".selectByUserName";

    private static final String INSERT_ACCESS_CHANNEL = NAMESPACE + ".insertAccessChannel";

    private static final String UPDATE_ACCESS_CHANNEL = NAMESPACE + ".updateAccessChannel";

    private static final String SELECT_ACCESS_CHANNEL = NAMESPACE + ".selectAccessChannel";

    @Resource(name = Constants.SQL_SESSION_TEMPLATE)
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    @Caching(evict = {
            @CacheEvict(key = "#user.id", cacheNames = CACHE_NAME, cacheManager = Constants.CACHE_MANAGER),
            @CacheEvict(key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{'Phone', #user.phone})", cacheNames = CACHE_NAME, cacheManager = Constants.CACHE_MANAGER)
    })
    @Override
    public int insert(User user) {
        return getSqlSession().insert(INSERT, user);
    }

    @Caching(evict = {
            @CacheEvict(key = "#user.id", cacheNames = CACHE_NAME, cacheManager = Constants.CACHE_MANAGER),
            @CacheEvict(key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{'Phone', #user.phone})", cacheNames = CACHE_NAME, cacheManager = Constants.CACHE_MANAGER)
    })
    @Override
    public int update(User user) {
        return getSqlSession().update(UPDATE, user);
    }

    @Override
    public boolean existsConflict(User user) {
        return 0 < (Integer) getSqlSession().selectOne(EXISTS_CONFLICT, user);
    }

    @Cacheable(key = "#id", cacheNames = CACHE_NAME, cacheManager = Constants.CACHE_MANAGER)
    @Override
    public User selectById(BigDecimal id) {
        return getSqlSession().selectOne(SELECT_BY_ID, id);
    }

    @Cacheable(key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{'Phone', #phone})", cacheNames = CACHE_NAME, cacheManager = Constants.CACHE_MANAGER)
    @Override
    public User selectByPhone(String phone) {
        return getSqlSession().selectOne(SELECT_BY_PHONE, phone);
    }

    @Cacheable(key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{'UserName', #userName})", cacheNames = CACHE_NAME, cacheManager = Constants.CACHE_MANAGER)
    @Override
    public User selectByUserName(String userName) {
        return getSqlSession().selectOne(SELECT_BY_USERNAME, userName);
    }

    @Override
    public int insertAccessChannel(UserAccessChannel accessChannel) {
        return getSqlSession().insert(INSERT_ACCESS_CHANNEL, accessChannel);
    }

    @Caching(evict = {
            @CacheEvict(key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{'UACChannel', #accessChannel.userId, #accessChannel.accessChannelId})", condition = "#result > 0", cacheNames = CACHE_NAME, cacheManager = Constants.CACHE_MANAGER),
            @CacheEvict(key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{'UACChannelId', #accessChannel.accessChannelId, #accessChannel.userIdOfAccessChannel})", condition = "#result > 0", cacheNames = CACHE_NAME, cacheManager = Constants.CACHE_MANAGER),
            @CacheEvict(key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{'UACUserChannelId', #accessChannel.userId, #accessChannel.accessChannelId, #accessChannel.userIdOfAccessChannel})", condition = "#result > 0", cacheNames = CACHE_NAME, cacheManager = Constants.CACHE_MANAGER)
    })
    @Override
    public int updateAccessChannel(UserAccessChannel accessChannel) {
        return getSqlSession().update(UPDATE_ACCESS_CHANNEL, accessChannel);
    }

    @Caching(cacheable = {
            @Cacheable(key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{'UACChannel', #userId, #channelId})", condition = "#result != null", cacheNames = CACHE_NAME, cacheManager = Constants.CACHE_MANAGER),
            @Cacheable(key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{'UACChannelId', #channelId, #userChannelId})", condition = "#result != null", cacheNames = CACHE_NAME, cacheManager = Constants.CACHE_MANAGER),
            @Cacheable(key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{'UACUserChannelId', #userId, #channelId, #userChannelId})", condition = "#result != null", cacheNames = CACHE_NAME, cacheManager = Constants.CACHE_MANAGER)
    })
    @Override
    public UserAccessChannel selectAccessChannelByUserChannelId(BigDecimal userId, BigDecimal channelId, String userChannelId) {
        HashMap<String, Object> parameter = new HashMap<>();
        parameter.put("userId", userId);
        parameter.put("accessChannelId", channelId);
        parameter.put("userIdOfAccessChannel", userChannelId);

        return getSqlSession().selectOne(SELECT_ACCESS_CHANNEL, parameter);
    }

    @Caching(cacheable = {
            @Cacheable(key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{'UACChannel', #result.userId, #result.accessChannelId})", condition = "#result != null", cacheNames = CACHE_NAME, cacheManager = Constants.CACHE_MANAGER),
            @Cacheable(key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{'UACChannelId', #channelId, #userChannelId})", condition = "#result != null", cacheNames = CACHE_NAME, cacheManager = Constants.CACHE_MANAGER),
            @Cacheable(key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{'UACUserChannelId', #result.userId, #result.accessChannelId, #result.userIdOfAccessChannel})", condition = "#result != null", cacheNames = CACHE_NAME, cacheManager = Constants.CACHE_MANAGER)
    })
    @Override
    public UserAccessChannel selectAccessChannelByChannelId(BigDecimal channelId, String userChannelId) {
        return selectAccessChannelByUserChannelId(null, channelId, userChannelId);
    }

    @Caching(cacheable = {
            @Cacheable(key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{'UACChannel', #userId, #channelId})", condition = "#result != null", cacheNames = CACHE_NAME, cacheManager = Constants.CACHE_MANAGER),
            @Cacheable(key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{'UACChannelId', #result.accessChannelId, #result.userIdOfAccessChannel})", condition = "#result != null", cacheNames = CACHE_NAME, cacheManager = Constants.CACHE_MANAGER),
            @Cacheable(key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{'UACUserChannelId', #result.userId, #result.accessChannelId, #result.userIdOfAccessChannel})", condition = "#result != null", cacheNames = CACHE_NAME, cacheManager = Constants.CACHE_MANAGER)
    })
    @Override
    public UserAccessChannel selectAccessChannelByUserChannel(BigDecimal userId, BigDecimal channelId) {
        return selectAccessChannelByUserChannelId(userId, channelId, null);
    }

    @Cacheable(key = "T(org.apache.commons.lang3.ArrayUtils).toString(new Object[]{'UAC', #userId})", cacheNames = CACHE_NAME, cacheManager = Constants.CACHE_MANAGER)
    @Override
    public List<UserAccessChannel> selectAccessChannelUserChannelList(BigDecimal userId) {
        HashMap<String, Object> parameter = new HashMap<>();
        parameter.put("userId", userId);
        return getSqlSession().selectList(SELECT_ACCESS_CHANNEL, parameter);
    }
}
