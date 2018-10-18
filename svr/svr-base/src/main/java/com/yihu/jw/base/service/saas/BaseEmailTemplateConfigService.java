package com.yihu.jw.base.service.saas;

import com.yihu.jw.base.dao.saas.BaseEmailTemplateConfigDao;
import com.yihu.jw.entity.base.saas.BaseEmailTemplateConfigDO;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service - BaseEmailTemplateConfig
 * Created by zdm on 2018/8/14.
 */
@Service
public class BaseEmailTemplateConfigService extends BaseJpaService<BaseEmailTemplateConfigDO, BaseEmailTemplateConfigDao> {

    @Autowired
    private BaseEmailTemplateConfigDao baseEmailTemplateConfigDao;

    public BaseEmailTemplateConfigDO findById(String id) {
        return baseEmailTemplateConfigDao.findById(id);
    }
    public BaseEmailTemplateConfigDO findByCode(String code) {
        return baseEmailTemplateConfigDao.findByCode(code);
    }

}
