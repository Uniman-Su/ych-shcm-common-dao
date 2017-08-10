package com.ych.shcm.o2o.dao;

import java.math.BigDecimal;

import com.ych.shcm.o2o.model.Operator;
import com.ych.shcm.o2o.model.OperatorThirdAuth;
import com.ych.shcm.o2o.model.ThirdAuthType;
import com.ych.shcm.o2o.model.UserThirdAuth;

/**
 * 用户第三方认证数据的Dao
 * <p>
 * Created by U on 2017/7/13.
 */
public interface OperatorThirdAuthDao {

    /**
     * 插入第三方认证信息
     *
     * @param operatorThirdAuth
     *         第三方认证信息
     * @return 插入的行数
     */
    int insert(OperatorThirdAuth operatorThirdAuth);

    /**
     * 根据第三方认证类型查询第三方认证信息
     *
     * @param operatorId
     *         操作员ID
     * @param thirdAuthType
     *         第三方认证类型
     * @return 第三方认证信息
     */
    OperatorThirdAuth selectByThirdType(BigDecimal operatorId, ThirdAuthType thirdAuthType);

    /**
     * 根据第三方ID查询第三方认证信息
     *
     * @param thirdAuthType
     *         第三方类型
     * @param thirdId
     *         第三方ID
     * @return 第三方认证信息
     */
    OperatorThirdAuth selectByThirdId(ThirdAuthType thirdAuthType, String thirdId);

}
