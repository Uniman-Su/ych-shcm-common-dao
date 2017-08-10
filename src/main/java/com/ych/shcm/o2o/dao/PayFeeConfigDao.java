package com.ych.shcm.o2o.dao;

import com.ych.shcm.o2o.model.PayChannel;
import com.ych.shcm.o2o.model.PayFeeConfig;

/**
 * 支付费用配置的Dao
 * 
 * @author U
 *
 */
public interface PayFeeConfigDao {

	/**
	 * 支付费用配置
	 */
	String CACHE_NAME = "PayFeeConfig";

	/**
	 * 根据支付渠道获取支付费用配置
	 * 
	 * @param payChannel
	 *            支付渠道
	 * @return 费用配置
	 */
	PayFeeConfig selectByPayChannel(PayChannel payChannel);


}
