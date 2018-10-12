package com.yihu.jw.base.service.module;

import com.yihu.jw.base.dao.module.SaasTypeModuleDao;
import com.yihu.jw.entity.base.module.SaasTypeModuleDO;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Entity - 租户类型模块
 * Created by yeshijie on 2018/10/11.
 */
@Service
public class SaasTypeModuleService extends BaseJpaService<SaasTypeModuleDO, SaasTypeModuleDao> {
    @Autowired
    private SaasTypeModuleDao saasTypeModuleDao;

    public void deleteBySaasTypeId(String saasTypeId) {
        saasTypeModuleDao.deleteBySaasTypeId(saasTypeId);
    }
}