package com.ych.shcm.o2o.dao.mybatis;

import com.ych.core.dao.BaseSqlSessionDaoSupport;
import com.ych.core.model.PagedList;
import com.ych.shcm.o2o.dao.ServiceItemDao;
import com.ych.shcm.o2o.model.Constants;
import com.ych.shcm.o2o.model.ServiceItem;
import com.ych.shcm.o2o.parameter.QueryServiceItemListParameter;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by mxp on 2017/7/13.
 */
@Lazy
@Repository("shcm.o2o.dao.mybatis.ServiceItemDao")
public class ServiceItemDaoImpl extends BaseSqlSessionDaoSupport implements ServiceItemDao {

    private static final String NAMESPACE = "com.ych.shcm.o2o.dao.mybatis.ServiceItemMapper";

    private static final String INSERT = NAMESPACE + ".insert";

    private static final String UPDATE = NAMESPACE + ".update";

    private static final String DELETE = NAMESPACE + ".delete";

    private static final String SELECT_BY_ID = NAMESPACE + ".selectById";

    private static final String SELECT_LIST = NAMESPACE + ".selectServiceItemList";

    private static final String SELECT_SERVICE_ITEMS_OF_PACK = NAMESPACE + ".selectServiceItemsOfPack";

    @Resource(name = Constants.SQL_SESSION_TEMPLATE)
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    /**
     * 根据ID查询服务项目
     *
     * @param id
     *         服务项目ID
     * @return 服务项目
     */
    @Override
    public ServiceItem selectById(BigDecimal id) {
        return getSqlSession().selectOne(SELECT_BY_ID, id);
    }

    /**
     * 更新服务项目数据
     *
     * @param serviceItem
     *         服务项目数据
     * @return 修改影响的行数
     */
    @Override
    public int update(ServiceItem serviceItem) {
        return getSqlSession().update(UPDATE, serviceItem);
    }

    /**
     * 插入服务项目
     *
     * @param serviceItem
     *         服务项目数据
     * @return 插入的行数
     */
    @Override
    public int insert(ServiceItem serviceItem) {
        return getSqlSession().insert(INSERT, serviceItem);
    }

    /**
     * 删除服务项目
     *
     * @param id
     *         服务项目id
     * @return 删除的
     */
    @Override
    public int delete(BigDecimal id) {
        return getSqlSession().delete(DELETE, id);
    }

    /**
     * 查询服务项目列表
     *
     * @param parameter
     *         查询参数
     * @return 查询结果ser
     */
    @Override
    public PagedList<ServiceItem> selectServiceItemList(QueryServiceItemListParameter parameter) {
        return selectPaged(SELECT_LIST, parameter);
    }

    /**
     * 查询服务包下的关联项目
     *
     * @param id
     *         服务包id
     * @return 项目列表
     */
    @Override
    public List<ServiceItem> selectServiceItemsOfPack(BigDecimal id) {
        return getSqlSession().selectList(SELECT_SERVICE_ITEMS_OF_PACK,id);
    }
}
