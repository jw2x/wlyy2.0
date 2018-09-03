package com.yihu.jw.base.service.module;

import com.yihu.jw.entity.base.module.ModuleDO;
import com.yihu.jw.base.dao.module.ModuleDao;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service - 模块
 * Created by chenweida on 2017/5/19.
 */
@Service
public class ModuleService extends BaseJpaService<ModuleDO, ModuleDao> {

    @Autowired
    private ModuleDao moduleDao;

}
