package com.yihu.jw.base.service.saas;

import com.yihu.jw.base.dao.module.ModuleDao;
import com.yihu.jw.base.dao.module.SaasTypeModuleDao;
import com.yihu.jw.base.dao.role.RoleDao;
import com.yihu.jw.base.dao.saas.SaasTypeDictDao;
import com.yihu.jw.base.dao.user.UserDao;
import com.yihu.jw.base.dao.user.UserRoleDao;
import com.yihu.jw.entity.base.module.ModuleDO;
import com.yihu.jw.entity.base.module.SaasTypeModuleDO;
import com.yihu.jw.entity.base.role.RoleDO;
import com.yihu.jw.entity.base.saas.SaasTypeDictDO;
import com.yihu.jw.entity.base.user.UserDO;
import com.yihu.jw.entity.base.user.UserRoleDO;
import com.yihu.mysql.query.BaseJpaService;
import com.yihu.utils.security.MD5;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zdm on 2018/10/10.
 */
@Service
@Transactional
public class SaasTypeDictService extends BaseJpaService<SaasTypeDictDO, SaasTypeDictDao> {

    @Autowired
    private SaasTypeDictDao saasTypeDictDao;
    @Autowired
    private ModuleDao moduleDao;
    @Autowired
    private SaasTypeModuleDao saasTypeModuleDao;


    public SaasTypeDictDO save(SaasTypeDictDO saasTypeDictDO, String saasTypeModuleIds) {
        String saasTypeId;
        if (StringUtils.isEmpty(saasTypeDictDO.getId())) {
            //新增
            saasTypeDictDO.setCode(getNextSaasTypeDictCode());
            saasTypeId = getCode();
        } else {
            //编辑
            saasTypeId = saasTypeDictDO.getId();
        }
        //根据moduleId获取模块
        String[] ids = saasTypeModuleIds.split(",");
        SaasTypeModuleDO saasTypeModuleDO;
        List<SaasTypeModuleDO> saasDefaultModuleDOS = new ArrayList<>();
        for (String id : ids) {
            ModuleDO moduleDO = moduleDao.findOne(id);
            saasTypeModuleDO = new SaasTypeModuleDO();
            saasTypeModuleDO.setModuleId(id);
            saasTypeModuleDO.setName(moduleDO.getName());
            saasTypeModuleDO.setParentModuleId(moduleDO.getParentId());
            saasTypeModuleDO.setType(moduleDO.getType());
            saasTypeModuleDO.setStatus(moduleDO.getStatus());
            saasTypeModuleDO.setIsEnd(moduleDO.getIsEnd());
            saasTypeModuleDO.setIsMust(moduleDO.getIsMust());
            saasTypeModuleDO.setUrl(moduleDO.getUrl());
            saasTypeModuleDO.setRemark(moduleDO.getRemark());
            saasTypeModuleDO.setSaasTypeId(saasTypeId);
            saasDefaultModuleDOS.add(saasTypeModuleDO);
        }
        //初始化租户默认模块
        saasTypeModuleDao.save(saasDefaultModuleDOS);
        //保存数据
        SaasTypeDictDO saasTypeDictDO1 = saasTypeDictDao.save(saasTypeDictDO);
        return saasTypeDictDO1;
    }

    /**
     * 获取当前最大租户编码+1
     *
     * @return
     */
    public Integer getNextSaasTypeDictCode() {
        return saasTypeDictDao.findTopCode() + 1;
    }

    /**
     * 验证
     *
     * @param id
     * @param name
     * @return
     */
    public boolean isSaasTypeDictExistByNameAndId(String id, String name) {
        String sql = "select count(1) from base_saas_type_dict  std where  std.name=:name and std.id!=:id";
        SQLQuery sqlQuery = currentSession().createSQLQuery(sql);
        sqlQuery.setParameter("id", id);
        sqlQuery.setParameter("name", name);
        BigInteger count = (BigInteger) sqlQuery.uniqueResult();
        return count.compareTo(new BigInteger("0")) > 0;
    }

    public SaasTypeDictDO findById(String id) {
        return saasTypeDictDao.findById(id);
    }
    public SaasTypeDictDO findByCode(Integer code) {
        return saasTypeDictDao.findByCode(code);
    }

}
