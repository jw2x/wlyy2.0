package com.yihu.jw.base.service;

import com.yihu.jw.base.dao.FunctionDao;
import com.yihu.jw.base.dao.ModuleDao;
import com.yihu.jw.entity.base.function.FunctionDO;
import com.yihu.jw.entity.base.module.ModuleDO;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by chenweida on 2017/5/19.
 */
@Service
public class FunctionService extends BaseJpaService<FunctionDO, FunctionDao> {

    @Autowired
    private FunctionDao functionDao;

}
