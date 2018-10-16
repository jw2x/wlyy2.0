package com.yihu.jw.base.service.area;

import com.yihu.jw.base.dao.area.BaseTownDao;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yihu.jw.entity.base.area.BaseTownDO;

/**
 * 区县字典服务service
 *
 * @version <pre>
 *                   Author	Version		Date		Changes
 *                   litaohong    1.0  2018年08月31日 Created
 *
 *                   </pre>
 * @since 1.
 */
@Service
public class BaseTownService extends BaseJpaService<BaseTownDO, BaseTownDao> {
    @Autowired
    private BaseTownDao baseTownDao;

    public String getNameByCode(String code) {
        return baseTownDao.getNameByCode(code);
    }
}
