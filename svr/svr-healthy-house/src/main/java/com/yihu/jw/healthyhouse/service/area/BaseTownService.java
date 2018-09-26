package com.yihu.jw.healthyhouse.service.area;

import com.yihu.jw.healthyhouse.dao.area.BaseTownDao;
import com.yihu.jw.healthyhouse.model.area.BaseTown;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * 区县字典服务service
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
public class BaseTownService extends BaseJpaService<BaseTown, BaseTownDao> {
    @Autowired
    public BaseTownDao baseTownDao;

    public String getCodeByname(String name) {
        BaseTown townDO = baseTownDao.findByName(name);
        if (townDO != null) {
            return townDO.getCode();
        } else {
            return null;
        }
    }
}
