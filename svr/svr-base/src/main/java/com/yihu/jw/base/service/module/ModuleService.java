package com.yihu.jw.base.service.module;

import com.yihu.jw.base.contant.CommonContant;
import com.yihu.jw.base.dao.module.ModuleDao;
import com.yihu.jw.base.dao.module.SaasModuleDao;
import com.yihu.jw.base.dao.module.SaasTypeModuleDao;
import com.yihu.jw.base.dao.saas.SaasDao;
import com.yihu.jw.base.dao.saas.SaasTypeDictDao;
import com.yihu.jw.entity.base.module.ModuleDO;
import com.yihu.jw.entity.base.module.SaasModuleDO;
import com.yihu.jw.entity.base.module.SaasTypeModuleDO;
import com.yihu.jw.entity.base.saas.SaasDO;
import com.yihu.jw.entity.base.saas.SaasTypeDictDO;
import com.yihu.mysql.query.BaseJpaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Service - 模块
 * Created by chenweida on 2017/5/19.
 */
@Service
public class ModuleService extends BaseJpaService<ModuleDO, ModuleDao> {

    @Autowired
    private ModuleDao moduleDao;
    @Autowired
    private SaasModuleDao saasModuleDao;
    @Autowired
    private SaasTypeModuleDao saasTypeModuleDao;
    @Autowired
    private SaasTypeDictDao saasTypeDictDao;
    @Autowired
    private SaasDao saasDao;

    /**
     * 新增模块
     * @param moduleDO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ModuleDO addModule(ModuleDO moduleDO){
        if(StringUtils.isBlank(moduleDO.getParentId())){
            moduleDO.setParentId(CommonContant.DEFAULT_PARENTID);
        }
        moduleDO.setDel(1);
        moduleDao.save(moduleDO);
        //若新增某必选业务模块，则需为所有已创建的租户和租户类型添加此业务模块
        addSubModule(moduleDO);

        return moduleDO;
    }

    /**
     * 新增模块时，新增租户和租户类型的模块
     * @param moduleDO
     */
    public void addSubModule(ModuleDO moduleDO){
        if (ModuleDO.Must.must.getValue().equals(moduleDO.getIsMust())){
            //判断父模块是否必选
            boolean parentIsMust = true;
            ModuleDO parentModule = null;
            if(!CommonContant.DEFAULT_PARENTID.equals(moduleDO.getParentId())){
                parentModule = moduleDao.findOne(moduleDO.getParentId());
                if(ModuleDO.Must.nonMust.getValue().equals(parentModule.getIsMust())){
                    parentIsMust = false;
                }
            }

            //租户类型
            Iterable<SaasTypeDictDO> saasTypeDictDOs = saasTypeDictDao.findAll();
            List<SaasTypeModuleDO> saasTypeModuleDOList = new ArrayList<>(16);
            while (saasTypeDictDOs.iterator().hasNext()){
                SaasTypeDictDO saasTypeDictDO = saasTypeDictDOs.iterator().next();
                SaasTypeModuleDO saasTypeModuleDO = new SaasTypeModuleDO();
                saasTypeModuleDO.setCreateTime(new Date());
                saasTypeModuleDO.setDel(moduleDO.getDel());
                saasTypeModuleDO.setStatus(moduleDO.getStatus());
                saasTypeModuleDO.setIsEnd(moduleDO.getIsEnd());
                saasTypeModuleDO.setIsMust(moduleDO.getIsMust());
                saasTypeModuleDO.setModuleId(moduleDO.getId());
                saasTypeModuleDO.setParentModuleId(moduleDO.getParentId());
                saasTypeModuleDO.setName(moduleDO.getName());
                saasTypeModuleDO.setRemark(moduleDO.getRemark());
                saasTypeModuleDO.setSaasTypeId(saasTypeDictDO.getId());
                saasTypeModuleDO.setType(moduleDO.getType());
                saasTypeModuleDO.setUrl(moduleDO.getUrl());
                saasTypeModuleDOList.add(saasTypeModuleDO);

                //父模块非必选
                if(!parentIsMust){
                    int count = saasTypeModuleDao.isExistModule(parentModule.getId());
                    if(count==0){
                        SaasTypeModuleDO typeModuleDO = new SaasTypeModuleDO();
                        typeModuleDO.setCreateTime(new Date());
                        typeModuleDO.setDel(parentModule.getDel());
                        typeModuleDO.setStatus(parentModule.getStatus());
                        typeModuleDO.setIsEnd(parentModule.getIsEnd());
                        typeModuleDO.setIsMust(parentModule.getIsMust());
                        typeModuleDO.setModuleId(parentModule.getId());
                        typeModuleDO.setParentModuleId(parentModule.getParentId());
                        typeModuleDO.setName(parentModule.getName());
                        typeModuleDO.setRemark(parentModule.getRemark());
                        typeModuleDO.setSaasTypeId(saasTypeDictDO.getId());
                        typeModuleDO.setType(parentModule.getType());
                        typeModuleDO.setUrl(parentModule.getUrl());
                        saasTypeModuleDOList.add(typeModuleDO);
                    }
                }
            }

            saasTypeModuleDao.save(saasTypeModuleDOList);

            //租户
            Iterable<SaasDO> saasDOs = saasDao.findAll();
            List<SaasModuleDO> saasModuleDOList = new ArrayList<>(16);
            while (saasDOs.iterator().hasNext()){
                SaasDO saasDO = saasDOs.iterator().next();
                SaasModuleDO saasModuleDO = new SaasModuleDO();
                saasModuleDO.setCreateTime(new Date());
                saasModuleDO.setDel(moduleDO.getDel());
                saasModuleDO.setStatus(moduleDO.getStatus());
                saasModuleDO.setIsEnd(moduleDO.getIsEnd());
                saasModuleDO.setIsMust(moduleDO.getIsMust());
                saasModuleDO.setModuleId(moduleDO.getId());
                saasModuleDO.setParentModuleId(moduleDO.getParentId());
                saasModuleDO.setName(moduleDO.getName());
                saasModuleDO.setRemark(moduleDO.getRemark());
                saasModuleDO.setSaasId(saasDO.getId());
                saasModuleDO.setType(moduleDO.getType());
                saasModuleDO.setUrl(moduleDO.getUrl());
                saasModuleDOList.add(saasModuleDO);

                //父模块非必选
                if(!parentIsMust){
                    int count = saasModuleDao.isExistModule(parentModule.getId());
                    if(count==0){
                        SaasModuleDO saasModule = new SaasModuleDO();
                        saasModule.setCreateTime(new Date());
                        saasModule.setDel(parentModule.getDel());
                        saasModule.setStatus(parentModule.getStatus());
                        saasModule.setIsEnd(parentModule.getIsEnd());
                        saasModule.setIsMust(parentModule.getIsMust());
                        saasModule.setModuleId(parentModule.getId());
                        saasModule.setParentModuleId(parentModule.getParentId());
                        saasModule.setName(parentModule.getName());
                        saasModule.setRemark(parentModule.getRemark());
                        saasModule.setSaasId(saasDO.getId());
                        saasModule.setType(parentModule.getType());
                        saasModule.setUrl(parentModule.getUrl());
                        saasModuleDOList.add(saasModule);
                    }
                }
            }

            saasModuleDao.save(saasModuleDOList);
        }
    }

    /**
     * 设置生效和失效
     * @param id
     * @param status
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateStatus(String id,Integer status){
        ModuleDO moduleDO = moduleDao.findOne(id);

        //若原业务模块为失效/生效，操作生效/失效后的变更逻辑如以下流程图所示
        if(ModuleDO.Status.available.getValue().equals(status)){
            //生效
            available(moduleDO);
        }else {
            //失效
            unavailable(moduleDO);
        }
    }

    /**
     * 生效
     * @param moduleDO
     */
    public void available(ModuleDO moduleDO){
        moduleDO.setStatus(ModuleDO.Status.unAvailable.getValue());
        moduleDao.save(moduleDO);

        addSubModule(moduleDO);

        ModuleDO parentModule = moduleDao.findOne(moduleDO.getParentId());
        if(ModuleDO.Status.unAvailable.getValue().equals(parentModule.getStatus())){
            available(moduleDO);
        }
    }


    /**
     * 失效
     * @param moduleDO
     */
    public void unavailable(ModuleDO moduleDO){
        //把本身失效
        moduleDO.setStatus(ModuleDO.Status.unAvailable.getValue());
        moduleDao.save(moduleDO);

        saasTypeModuleDao.deleteByModuleId(moduleDO.getId());
        saasModuleDao.deleteByModuleId(moduleDO.getId());

        //把子类失效
        List<ModuleDO> moduleDOList = moduleDao.findByParentId(moduleDO.getId());
        moduleDOList.forEach(module->{
            if(ModuleDO.Status.available.getValue().equals(module.getStatus())){
                unavailable(moduleDO);
            }
        });
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
