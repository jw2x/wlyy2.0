package com.yihu.jw.healthyhouse.service.area;

import com.yihu.jw.healthyhouse.dao.area.BaseCityDao;
import com.yihu.jw.healthyhouse.model.area.BaseCityDO;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 城市字典服务service
 *
 * @version <pre>
 * Author	Version		Date		Changes
 * litaohong    1.0  2018年08月31日 Created
 *
 * </pre>
 * @since 1.
 */
@Service
public class BaseCityService extends BaseJpaService<BaseCityDO, BaseCityDao> {

    @Autowired
    private BaseCityDao baseCityDao;

    public String getCodeByname(String name) {
        BaseCityDO cityDO = baseCityDao.findByName(name);
        if (cityDO != null) {
            return cityDO.getCode();
        } else {
            return null;
        }

    }

}
