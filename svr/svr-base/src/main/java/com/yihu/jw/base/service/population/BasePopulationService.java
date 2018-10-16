package com.yihu.jw.base.service.population;

import com.yihu.jw.base.dao.population.BasePopulationDao;
import com.yihu.jw.entity.base.population.BasePopulationDO;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 基础人口基数信息服务service
 *
 * @version <pre>
 *          Author	Version		Date		Changes
 *          litaohong    1.0  2018年09月26日 update
 *
 *          </pre>
 * @since 1.
 */
@Service
public class BasePopulationService extends BaseJpaService<BasePopulationDO, BasePopulationDao> {
    @Autowired
    private BasePopulationDao basePopulationDao;

    public BasePopulationDO findById(String id) {
        return basePopulationDao.findOne(id);
    }
}
