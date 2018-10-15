package com.yihu.jw.base.service.area;

import com.yihu.jw.base.dao.area.BaseCityDao;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yihu.jw.entity.base.area.BaseCityDO;

/**
 * 城市字典服务service
 *
 * @version <pre>
 *          Author	Version		Date		Changes
 *          litaohong    1.0  2018年08月31日 Created
 *
 *          </pre>
 * @since 1.
 */
@Service
public class BaseCityService extends BaseJpaService<BaseCityDO, BaseCityDao> {
    @Autowired
    private BaseCityDao baseCityDao;

    public String getNameByCode(String code) {
        return baseCityDao.getNameByCode(code);
    }
}
