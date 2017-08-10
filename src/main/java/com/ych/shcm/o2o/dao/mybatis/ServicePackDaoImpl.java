package com.ych.shcm.o2o.dao.mybatis;

import com.ych.core.dao.BaseSqlSessionDaoSupport;
import com.ych.core.model.PagedList;
import com.ych.shcm.o2o.dao.ServicePackDao;
import com.ych.shcm.o2o.model.Constants;
import com.ych.shcm.o2o.model.ServicePack;
import com.ych.shcm.o2o.parameter.QueryServicePackListParameter;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * Created by mxp on 2017/7/13.
 */
@Lazy
@Repository("shcm.o2o.dao.mybatis.ServicePackDao")
public class ServicePackDaoImpl extends BaseSqlSessionDaoSupport implements ServicePackDao {

    private static final String NAMESPACE = "com.ych.shcm.o2o.dao.mybatis.ServicePackMapper";

    private static final String INSERT = NAMESPACE + ".insert";

    private static final String UPDATE = NAMESPACE + ".update";

    private static final String DELETE = NAMESPACE + ".delete";

    private static final String SELECT_BY_ID = NAMESPACE + ".selectById";

    private static final String SELECT_LIST = NAMESPACE + ".selectServicePackList";

    @Resource(name = Constants.SQL_SESSION_TEMPLATE)
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    /**
     * 根据ID查询服务包
     *
     * @param id
     *         服务包ID
     * @return 服务包
     */
    @Override
    public ServicePack selectById(BigDecimal id) {
        return getSqlSession().selectOne(SELECT_BY_ID, id);
    }

    /**
     * 更新服务包数据
     *
     * @param servicePack
     *         服务包数据
     * @return 修改影响的行数
     */
    @Override
    public int update(ServicePack servicePack) {
        return getSqlSession().update(UPDATE, servicePack);
    }

    /**
     * 插入服务包
     *
     * @param servicePack
     *         服务包数据
     * @return 插入的行数
     */
    @Override
    public int insert(ServicePack servicePack) {
        return getSqlSession().insert(INSERT, servicePack);
    }

    /**
     * 删除服务包
     *
     * @param id
     *         服务包id
     * @return 删除的
     */
    @Override
    public int delete(BigDecimal id) {
        return getSqlSession().delete(DELETE, id);
    }

    /**
     * 查询服务包列表
     *
     * @param parameter
     *         查询参数
     * @return 查询结果
     */
    @Override
    public PagedList<ServicePack> selectServicePackList(QueryServicePackListParameter parameter) {
        return selectPaged(SELECT_LIST, parameter);

    }
}
