package com.yihu.jw.base.service.saas;

import com.yihu.jw.base.dao.function.FunctionDao;
import com.yihu.jw.base.dao.role.RoleDao;
import com.yihu.jw.base.dao.saas.SaasTypeDictDao;
import com.yihu.jw.base.dao.saas.SaasDefaultModuleFunctionDao;
import com.yihu.jw.base.dao.saas.SaasModuleFunctionDao;
import com.yihu.jw.base.dao.user.UserDao;
import com.yihu.jw.base.dao.user.UserRoleDao;
import com.yihu.jw.entity.base.function.FunctionDO;
import com.yihu.jw.entity.base.role.RoleDO;
import com.yihu.jw.entity.base.saas.SaasTypeDictDO;
import com.yihu.jw.entity.base.saas.SaasDefaultModuleFunctionDO;
import com.yihu.jw.entity.base.saas.SaasModuleFunctionDO;
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
    private FunctionDao functionDao;
    @Autowired
    private SaasDefaultModuleFunctionDao saasDefaultModuleFunctionDao;


    public SaasTypeDictDO save(SaasTypeDictDO saasTypeDictDO, String saasTypeDefaultModuleIds) {
        //初始化租户信息
        Integer code ;
        if (StringUtils.isEmpty(saasTypeDictDO.getId())) {
            //新增
            code = getNextSaasTypeDictCode();
            saasTypeDictDO.setCode(code);
        } else {
            //编辑
            code = saasTypeDictDO.getCode();
        }
        //根据moduleId获取模块关联的接口id
        String[] ids = saasTypeDefaultModuleIds.split(",");
        List<FunctionDO> functionDOList = functionDao.findFunctionDOSByModuleIdExists(ids);
        //初始化租户默认模块
        List<SaasDefaultModuleFunctionDO> saasDefaultModuleDOS = new ArrayList<>();
        functionDOList.forEach(item -> {
            SaasDefaultModuleFunctionDO saasDefaultModuleFunctionDO = new SaasDefaultModuleFunctionDO();
            saasDefaultModuleFunctionDO.setSaasType(code);
            saasDefaultModuleFunctionDO.setModuleId(item.getModuleId());
            saasDefaultModuleFunctionDO.setFunctionId(item.getId());
            saasDefaultModuleDOS.add(saasDefaultModuleFunctionDO);
        });
        //保存数据
        saasTypeDictDO = saasTypeDictDao.save(saasTypeDictDO);
        saasDefaultModuleFunctionDao.save(saasDefaultModuleDOS);
        return saasTypeDictDO;
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
}
