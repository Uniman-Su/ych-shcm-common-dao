package com.ych.shcm.o2o.dao.mybatis;

import com.ych.core.dao.BaseSqlSessionDaoSupport;
import com.ych.core.model.PagedList;
import com.ych.shcm.o2o.dao.UserCarDao;
import com.ych.shcm.o2o.model.CarUserHistory;
import com.ych.shcm.o2o.model.Constants;
import com.ych.shcm.o2o.model.UserCar;
import com.ych.shcm.o2o.parameter.QueryCarUserHistoryParameter;
import com.ych.shcm.o2o.parameter.QueryUserCarParameter;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;

/**
 * 用户的车辆信息Dao实现
 * <p>
 * Created by U on 2017/7/10.
 */
@Lazy
@Repository("shcm.o2o.dao.mybatis.UserCarDao")
public class UserCarDaoImpl extends BaseSqlSessionDaoSupport implements UserCarDao {

    private static final String NAMESPACE = "com.ych.shcm.o2o.dao.mybatis.UserCarMapper";

    private static final String INSERT_USER_CAR = NAMESPACE + ".insertUserCar";

    private static final String DELETE_USER_CAR_BY_ID = NAMESPACE + ".deleteUserCarById";

    private static final String SELECT_USER_CAR_BY_ID = NAMESPACE + ".selectUserCarById";

    private static final String SELECT_ONE_BY_USER_ID = NAMESPACE + ".selectOneByUserId";

    private static final String SELECT_USER_CAR_BY_USER_ID_AND_CAR_ID = NAMESPACE + ".selectUserCarByUserIdAndCarId";

    private static final String SELECT_USER_CAR_LIST = NAMESPACE + ".selectUserCarList";

    private static final String INSERT_USER_HISTORY = NAMESPACE + ".insertUserHistory";

    private static final String SELECT_USER_HISTORY_LIST = NAMESPACE + ".selectUserHistoryList";

    private static final String SELECT_BY_CAR_ID = NAMESPACE + ".selectUserCarByCarId";

    @Resource(name = Constants.SQL_SESSION_TEMPLATE)
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    @Override
    public int insertUserCar(UserCar userCar) {
        return getSqlSession().insert(INSERT_USER_CAR, userCar);
    }

    @Override
    public int deleteUserCarById(BigDecimal id) {
        return getSqlSession().delete(DELETE_USER_CAR_BY_ID, id);
    }

    @Override
    public UserCar selectUserCarById(BigDecimal id) {
        return getSqlSession().selectOne(SELECT_USER_CAR_BY_ID, id);
    }

    @Override
    public UserCar selectOneByUserId(BigDecimal userId) {
        return getSqlSession().selectOne(SELECT_ONE_BY_USER_ID, userId);
    }

    @Override
    public UserCar selectUserCarByUserIdAndCarId(BigDecimal userId, BigDecimal carId) {
        HashMap<String, Object> parameter = new HashMap<>();
        parameter.put("userId", userId);
        parameter.put("carId", carId);
        return getSqlSession().selectOne(SELECT_USER_CAR_BY_USER_ID_AND_CAR_ID, parameter);
    }

    @Override
    public PagedList<UserCar> selectPagedUserCarList(QueryUserCarParameter parameter) {
        return selectPaged(SELECT_USER_CAR_LIST, parameter);
    }

    @Override
    public int insertUserHistory(CarUserHistory userHistory) {
        return getSqlSession().insert(INSERT_USER_HISTORY, userHistory);
    }

    @Override
    public PagedList<CarUserHistory> selectPagedUserHistoryList(QueryCarUserHistoryParameter parameter) {
        return selectPaged(SELECT_USER_HISTORY_LIST, parameter);
    }

    /**
     * 通过车辆id查询车辆和用户关联
     *
     * @param id
     * @return
     */
    @Override
    public UserCar selectUserCarByCarId(BigDecimal id) {
        return getSqlSession().selectOne(SELECT_BY_CAR_ID, id);

    }
}
