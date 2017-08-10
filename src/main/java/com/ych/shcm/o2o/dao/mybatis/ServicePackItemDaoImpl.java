package com.ych.shcm.o2o.dao.mybatis;

import com.ych.core.dao.BaseSqlSessionDaoSupport;
import com.ych.shcm.o2o.dao.ServicePackItemDao;
import com.ych.shcm.o2o.model.Constants;
import com.ych.shcm.o2o.model.ServiceItem;
import com.ych.shcm.o2o.model.ServicePackItem;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by mxp on 2017/7/13.
 */
@Lazy
@Repository("shcm.o2o.dao.mybatis.ServicePackItemDao")
public class ServicePackItemDaoImpl extends BaseSqlSessionDaoSupport implements ServicePackItemDao {

    private static final String NAMESPACE = "com.ych.shcm.o2o.dao.mybatis.ServicePackItemMapper";

    private static final String INSERT = NAMESPACE + ".insert";

    private static final String INSERT_LIST = NAMESPACE + ".insertList";

    private static final String UPDATE = NAMESPACE + ".update";

    private static final String DELETE = NAMESPACE + ".delete";

    private static final String SELECT_BY_ID = NAMESPACE + ".selectById";

    private static final String SELECT_SERVICE_ITEMS_BY_PACK_ID = NAMESPACE + ".selectServiceItemsByPackId";

    @Resource(name = Constants.SQL_SESSION_TEMPLATE)
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    /**
     * 根据ID查询服务包项目
     *
     * @param id
     *         服务包项目ID
     * @return 服务包项目
     */
    @Override
    public ServicePackItem selectById(BigDecimal id) {
        return getSqlSession().selectOne(SELECT_BY_ID, id);
    }

    /**
     * 查询服务包下关联的项目
     *
     * @param packId
     *         服务包id
     * @return 关联的项目
     */
    public List<ServiceItem> selectServiceItemsByPackId(BigDecimal packId) {
        return getSqlSession().selectList(SELECT_SERVICE_ITEMS_BY_PACK_ID, packId);

    }

    /**
     * 更新服务包项目数据
     *
     * @param servicePackItem
     *         服务包项目数据
     * @return 修改影响的行数
     */
    @Override
    public int update(ServicePackItem servicePackItem) {
        return getSqlSession().update(UPDATE, servicePackItem);
    }

    /**
     * 插入服务包项目
     *
     * @param servicePackItem
     *         服务包项目数据
     * @return 插入的行数
     */
    @Override
    public int insert(ServicePackItem servicePackItem) {
        return getSqlSession().insert(INSERT, servicePackItem);
    }

    /**
     * 批量插入服务包项目
     *
     * @param packId
     *         服务包ID
     * @param items
     *         项目列表
     * @return 插入的行数
     */
    @Override
    public int insertList(BigDecimal packId, Set<BigDecimal> items) {
        HashMap map = new HashMap();
        map.put("packId", packId);
        map.put("items", items);
        return getSqlSession().insert(INSERT_LIST, map);

    }

    /**
     * 删除服务包项目
     *
     * @param id
     *         服务包项目id
     * @return 删除的
     */
    @Override
    public int delete(BigDecimal id) {
        return getSqlSession().delete(DELETE, id);
    }

}
