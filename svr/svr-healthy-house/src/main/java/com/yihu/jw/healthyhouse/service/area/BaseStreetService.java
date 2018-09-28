package com.yihu.jw.healthyhouse.service.area;

import com.yihu.jw.healthyhouse.dao.area.BaseStreetDao;
import com.yihu.jw.healthyhouse.model.area.BaseStreet;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * 街道字典服务service
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong    1.0  2018年08月31日 Created
 *
 * </pre>
 * @since 1.
 */
@Service
public class BaseStreetService extends BaseJpaService<BaseStreet, BaseStreetDao> {
    @Autowired
    private BaseStreetDao baseStreetDao;

    public String getCodeByname(String name) {
        BaseStreet streetDO = baseStreetDao.findByName(name);
        if (streetDO != null) {
            return streetDO.getCode();
        } else {
            return null;
        }
    }
}
