package com.yihu.jw.base.service;

import com.yihu.jw.base.dao.ModuleFunctionDao;
import com.yihu.jw.entity.base.module.ModuleFunctionDO;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by chenweida on 2017/5/19.
 */
@Service
public class ModuleFunctionService extends BaseJpaService<ModuleFunctionDO, ModuleFunctionDao> {

    @Autowired
    private ModuleFunctionDao moduleFunctionDao;


}
