package com.ych.shcm.o2o.dao.mybatis;

import com.ych.core.dao.BaseSqlSessionDaoSupport;
import com.ych.core.model.PagedList;
import com.ych.shcm.o2o.dao.ShopSettleDao;
import com.ych.shcm.o2o.model.Constants;
import com.ych.shcm.o2o.model.ShopSettleDate;
import com.ych.shcm.o2o.model.ShopSettleDateSummary;
import com.ych.shcm.o2o.model.ShopSettleDetail;
import com.ych.shcm.o2o.parameter.QueryShopSettleParameter;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

/**
 * 店铺结算Dao的实现
 * <p>
 * Created by U on 2017/7/14.
 */
@Lazy
@Repository("shcm.o2o.dao.mybatis.ShopSettleDao")
public class ShopSettleDaoImpl extends BaseSqlSessionDaoSupport implements ShopSettleDao {

    private static final String NAMESPACE = "com.ych.shcm.o2o.dao.mybatis.ShopSettleMapper";

    private static final String INSERT_DETAIL = NAMESPACE + ".insertDetail";

    private static final String SELECT_DETAIL_LIST = NAMESPACE + ".selectDetailList";

    private static final String INSERT_DATE = NAMESPACE + ".insertDate";

    private static final String UPDATE_DATE_STATUS = NAMESPACE + ".updateDateStatus";

    private static final String ADD_DATE_MONEY = NAMESPACE + ".addDateMoney";

    private static final String SELECT_DATE = NAMESPACE + ".selectDate";

    private static final String SELECT_DATE_LIST = NAMESPACE + ".selectDateList";

    private static final String INSERT_SUMMARY = NAMESPACE + ".insertSummary";

    private static final String UPDATE_SUMMARY_STATUS = NAMESPACE + ".updateSummaryStatus";

    private static final String ADD_SUMMARY_MONEY = NAMESPACE + ".addSummaryMoney";

    private static final String SELECT_SUMMARY = NAMESPACE + ".selectSummary";

    private static final String SELECT_SUMMARY_LIST = NAMESPACE + ".selectSummaryList";

    private static final String CHECK_DATE_IF_SETTLED_ALL = NAMESPACE + ".checkDateIfSettledAll";

    @Resource(name = Constants.SQL_SESSION_TEMPLATE)
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    @Override
    public int insertDetail(ShopSettleDetail settleDetail) {
        return getSqlSession().insert(INSERT_DETAIL, settleDetail);
    }

    @Override
    public PagedList<ShopSettleDetail> selectPagedDetailList(QueryShopSettleParameter parameter) {
        return selectPaged(SELECT_DETAIL_LIST, parameter);
    }

    @Override
    public int insertDate(ShopSettleDate settleDate) {
        return getSqlSession().insert(INSERT_DATE, settleDate);
    }

    @Override
    public int updateDateStatus(ShopSettleDate settleDate) {
        return getSqlSession().update(UPDATE_DATE_STATUS, settleDate);
    }

    @Override
    public int addDateMoney(ShopSettleDate settleDate) {
        return getSqlSession().update(ADD_DATE_MONEY, settleDate);
    }

    @Override
    public ShopSettleDate selectDateById(BigDecimal id) {
        HashMap<String, Object> parameter = new HashMap<>();
        parameter.put("id", id);
        return getSqlSession().selectOne(SELECT_DATE, parameter);
    }

    @Override
    public ShopSettleDate selectDateByShopIdAndSettleDate(BigDecimal shopId, Date settleDate) {
        HashMap<String, Object> parameter = new HashMap<>();
        parameter.put("shopId", shopId);
        parameter.put("settleDate", settleDate);
        return getSqlSession().selectOne(SELECT_DATE, parameter);
    }

    @Override
    public PagedList<ShopSettleDate> selectPagedDateList(QueryShopSettleParameter parameter) {
        return selectPaged(SELECT_DATE_LIST, parameter);
    }

    @Override
    public int insertSummary(ShopSettleDateSummary settleSummary) {
        return getSqlSession().insert(INSERT_SUMMARY, settleSummary);
    }

    @Override
    public int updateSummaryStatus(ShopSettleDateSummary settleSummary) {
        return getSqlSession().update(UPDATE_SUMMARY_STATUS, settleSummary);
    }

    @Override
    public int addSummaryMoney(ShopSettleDateSummary settleSummary) {
        return getSqlSession().update(ADD_SUMMARY_MONEY, settleSummary);
    }

    @Override
    public ShopSettleDateSummary selectSummaryById(BigDecimal id) {
        HashMap<String, Object> parameter = new HashMap<>();
        parameter.put("id", id);
        return getSqlSession().selectOne(SELECT_SUMMARY, parameter);
    }

    @Override
    public ShopSettleDateSummary selectSummaryBySettleDate(Date settleDate) {
        HashMap<String, Object> parameter = new HashMap<>();
        parameter.put("settleDate", settleDate);
        return getSqlSession().selectOne(SELECT_SUMMARY, parameter);
    }

    @Override
    public PagedList<ShopSettleDateSummary> selectPagedSummaryList(QueryShopSettleParameter parameter) {
        return selectPaged(SELECT_SUMMARY_LIST, parameter);
    }

    /**
     * 检查本日结算是否完结
     *
     * @param settleDate
     * @return
     */
    @Override
    public boolean checkDateIfSettledAll(Date settleDate) {
        Long count = (Long) getSqlSession().selectOne(CHECK_DATE_IF_SETTLED_ALL, settleDate);
        return count.intValue() == 0;

    }
}
