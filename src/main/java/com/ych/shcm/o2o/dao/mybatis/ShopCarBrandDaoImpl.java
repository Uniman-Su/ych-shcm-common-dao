package com.ych.shcm.o2o.dao.mybatis;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.ych.core.dao.BaseSqlSessionDaoSupport;
import com.ych.shcm.o2o.dao.ShopCarBrandDao;
import com.ych.shcm.o2o.model.CarBrand;
import com.ych.shcm.o2o.model.Constants;
import com.ych.shcm.o2o.model.ShopCarBrand;

/**
 * 车型品牌Dao实现
 * <p>
 * Created by U on 2017/7/7.
 */
@Lazy
@Repository("shcm.o2o.dao.mybatis.ShopCarBrandDao")
public class ShopCarBrandDaoImpl extends BaseSqlSessionDaoSupport implements ShopCarBrandDao {

    private static final String NAMESPACE = "com.ych.shcm.o2o.dao.mybatis.ShopCarBrandMapper";

    private static final String BATCH_INSERT = NAMESPACE + ".batchInsert";

    private static final String DELETE_BY_ID = NAMESPACE + ".deleteById";

    private static final String DELETE_BY_SHOP_ID = NAMESPACE + ".deleteByShopId";

    private static final String SELECT_BY_SHOP_ID = NAMESPACE + ".selectByShopId";

    @Resource(name = Constants.SQL_SESSION_TEMPLATE)
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    @Override
    public int insert(List<ShopCarBrand> carBrands) {
        return getSqlSession().insert(BATCH_INSERT, carBrands);
    }

    @Override
    public int delete(BigDecimal... id) {
        if (id.length == 0) {
            return 0;
        }

        return getSqlSession().delete(DELETE_BY_ID, id);
    }

    @Override
    public int deleteByShopId(BigDecimal shopId) {
        return getSqlSession().delete(DELETE_BY_SHOP_ID, shopId);
    }

    @Override
    public List<CarBrand> selectByShopId(BigDecimal shopId) {
        return getSqlSession().selectList(SELECT_BY_SHOP_ID, shopId);
    }
}
