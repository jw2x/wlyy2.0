package com.yihu.jw.base.service.module;

import com.yihu.jw.base.contant.CommonContant;
import com.yihu.jw.base.dao.module.ModuleDao;
import com.yihu.jw.entity.base.module.ModuleDO;
import com.yihu.mysql.query.BaseJpaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service - 模块
 * Created by chenweida on 2017/5/19.
 */
@Service
public class ModuleService extends BaseJpaService<ModuleDO, ModuleDao> {

    @Autowired
    private ModuleDao moduleDao;

    /**
     * 新增模块
     * @param moduleDO
     * @return
     */
    public ModuleDO addModule(ModuleDO moduleDO){
        if(StringUtils.isBlank(moduleDO.getParentId())){
            moduleDO.setParentId(CommonContant.DEFAULT_PARENTID);
        }
        moduleDao.save(moduleDO);
        //若新增某必选业务模块，则需为所有已创建的租户和租户类型添加此业务模块

        return moduleDO;
    }

    /**
     * 设置生效和失效
     * @param id
     * @param status
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateStatus(String id,Integer status){
        moduleDao.updateStatus(id,status);
        //若原业务模块为失效/生效，操作生效/失效后的变更逻辑如以下流程图所示

    }

    /**
     * 名称是否存在
     * @param name
     * @return
     */
    public int isExistName(String name){
        return moduleDao.isExistName(name);
    }

}
