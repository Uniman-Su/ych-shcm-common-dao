package com.ych.shcm.o2o.dao;

import com.ych.core.model.PagedList;
import com.ych.shcm.o2o.model.ServiceProviderSettleDate;
import com.ych.shcm.o2o.model.ServiceProviderSettleDateSummary;
import com.ych.shcm.o2o.model.ServiceProviderSettleDetail;
import com.ych.shcm.o2o.parameter.QueryServiceProviderSettleParameter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 服务商结算Dao
 * <p>
 * Created by U on 2017/7/13.
 */
public interface ServiceProviderSettleDao {

    /**
     * 插入服务商结算明细
     *
     * @param serviceProviderSettleDetail
     *         服务商结算明细
     * @return 插入的行数
     */
    int insertDetail(ServiceProviderSettleDetail serviceProviderSettleDetail);

    /**
     * 查询服务商结算明细的分页列表
     *
     * @param parameter
     *         查询参数
     * @return 服务商结算明细的分页列表
     */
    PagedList<ServiceProviderSettleDetail> selectPagedDetailList(QueryServiceProviderSettleParameter parameter);

    /**
     * 插入结算日数据
     *
     * @param settleDate
     *         结算日数据
     * @return 插入的行数
     */
    int insertDate(ServiceProviderSettleDate settleDate);

    /**
     * 更新结算日状态
     *
     * @param settleDate
     *         结算日数据
     * @return 修改的行数
     */
    int updateDateStatus(ServiceProviderSettleDate settleDate);

    /**
     * 增加日结算数据的金额
     *
     * @param settleDate
     *         结算日数据
     * @return 更新的行数
     */
    int addDateMoney(ServiceProviderSettleDate settleDate);

    /**
     * 根据ID获取日结算数据
     *
     * @param id
     *         ID
     * @return 日结算数据
     */
    ServiceProviderSettleDate selectDateById(BigDecimal id);

    /**
     * 根据服务商ID和结算日获取日结算数据
     *
     * @param serviceProviderId
     *         服务商ID
     * @param settleDate
     *         结算日
     * @return 日结算数据
     */
    ServiceProviderSettleDate selectDateByServiceProviderIdAndSettleDate(BigDecimal serviceProviderId, Date settleDate);

    /**
     * 查询服务商结算日数据的分页数据
     *
     * @param parameter
     *         查询参数
     * @return 服务商结算日数据的分页数据
     */
    PagedList<ServiceProviderSettleDate> selectPagedDateList(QueryServiceProviderSettleParameter parameter);

    /**
     * 插入结算日数据汇总
     *
     * @param settleSummary
     *         结算日数据汇总
     * @return 插入的行数
     */
    int insertSummary(ServiceProviderSettleDateSummary settleSummary);

    /**
     * 更新结算日数据汇总状态
     *
     * @param settleSummary
     *         算日数据汇总
     * @return 修改的行数
     */
    int updateSummaryStatus(ServiceProviderSettleDateSummary settleSummary);

    /**
     * 增加结算日数据汇总的金额
     *
     * @param settleSummary
     *         算日数据汇总
     * @return 更新的行数
     */
    int addSummaryMoney(ServiceProviderSettleDateSummary settleSummary);

    /**
     * 根据ID获取结算日数据汇总
     *
     * @param id
     *         ID
     * @return 结算日数据汇总
     */
    ServiceProviderSettleDateSummary selectSummaryById(BigDecimal id);

    /**
     * 根据结算日获取结算日数据汇总
     *
     * @param settleDate
     *         结算日
     * @return 结算日数据汇总
     */
    ServiceProviderSettleDateSummary selectSummaryBySettleDate(Date settleDate);

    /**
     * 查询服务商结算日汇总数据的分页数据
     *
     * @param parameter
     *         查询参数
     * @return 服务商结算日汇总数据的分页数据
     */
    PagedList<ServiceProviderSettleDateSummary> selectPagedSummaryList(QueryServiceProviderSettleParameter parameter);

    /**
     * 检查本日结算是否完结
     *
     * @param settleDate
     * @return
     */
    boolean checkDateIfSettledAll(Date settleDate);
}
