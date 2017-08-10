package com.ych.shcm.o2o.dao;

import com.ych.core.model.PagedList;
import com.ych.shcm.o2o.model.ShopSettleDate;
import com.ych.shcm.o2o.model.ShopSettleDateSummary;
import com.ych.shcm.o2o.model.ShopSettleDetail;
import com.ych.shcm.o2o.parameter.QueryShopSettleParameter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 店铺结算Dao
 * <p>
 * Created by U on 2017/7/13.
 */
public interface ShopSettleDao {

    /**
     * 插入店铺结算明细
     *
     * @param settleDetail
     *         店铺结算明细
     * @return 插入的行数
     */
    int insertDetail(ShopSettleDetail settleDetail);

    /**
     * 查询店铺结算明细的分页列表
     *
     * @param parameter
     *         查询参数
     * @return 店铺结算明细的分页列表
     */
    PagedList<ShopSettleDetail> selectPagedDetailList(QueryShopSettleParameter parameter);

    /**
     * 插入结算日数据
     *
     * @param settleDate
     *         结算日数据
     * @return 插入的行数
     */
    int insertDate(ShopSettleDate settleDate);

    /**
     * 更新结算日状态
     *
     * @param settleDate
     *         结算日数据
     * @return 修改的行数
     */
    int updateDateStatus(ShopSettleDate settleDate);

    /**
     * 增加日结算数据的金额
     *
     * @param settleDate
     *         结算日数据
     * @return 更新的行数
     */
    int addDateMoney(ShopSettleDate settleDate);

    /**
     * 根据ID获取日结算数据
     *
     * @param id
     *         ID
     * @return 日结算数据
     */
    ShopSettleDate selectDateById(BigDecimal id);

    /**
     * 根据店铺ID和结算日获取日结算数据
     *
     * @param shopId
     *         店铺ID
     * @param settleDate
     *         结算日
     * @return 日结算数据
     */
    ShopSettleDate selectDateByShopIdAndSettleDate(BigDecimal shopId, Date settleDate);

    /**
     * 查询店铺结算日数据的分页数据
     *
     * @param parameter
     *         查询参数
     * @return 店铺结算日数据的分页数据
     */
    PagedList<ShopSettleDate> selectPagedDateList(QueryShopSettleParameter parameter);

    /**
     * 插入结算日数据汇总
     *
     * @param settleSummary
     *         结算日数据汇总
     * @return 插入的行数
     */
    int insertSummary(ShopSettleDateSummary settleSummary);

    /**
     * 更新结算日数据汇总状态
     *
     * @param settleSummary
     *         算日数据汇总
     * @return 修改的行数
     */
    int updateSummaryStatus(ShopSettleDateSummary settleSummary);

    /**
     * 增加结算日数据汇总的金额
     *
     * @param settleSummary
     *         算日数据汇总
     * @return 更新的行数
     */
    int addSummaryMoney(ShopSettleDateSummary settleSummary);

    /**
     * 根据ID获取结算日数据汇总
     *
     * @param id
     *         ID
     * @return 结算日数据汇总
     */
    ShopSettleDateSummary selectSummaryById(BigDecimal id);

    /**
     * 根据结算日获取结算日数据汇总
     *
     * @param settleDate
     *         结算日
     * @return 结算日数据汇总
     */
    ShopSettleDateSummary selectSummaryBySettleDate(Date settleDate);

    /**
     * 查询店铺结算日汇总数据的分页数据
     *
     * @param parameter
     *         查询参数
     * @return 店铺结算日汇总数据的分页数据
     */
    PagedList<ShopSettleDateSummary> selectPagedSummaryList(QueryShopSettleParameter parameter);

    /**
     * 检查本日结算是否完结
     * @param settleDate
     * @return
     */
    boolean checkDateIfSettledAll(Date settleDate);


}
