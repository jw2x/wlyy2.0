package com.yihu.jw.base.service;

import com.yihu.jw.base.dao.*;
import com.yihu.jw.entity.base.role.RoleDO;
import com.yihu.jw.entity.base.saas.SaasDO;
import com.yihu.jw.entity.base.saas.SaasDefaultModuleFunctionDO;
import com.yihu.jw.entity.base.saas.SaasModuleFunctionDO;
import com.yihu.jw.entity.base.user.UserDO;
import com.yihu.jw.entity.base.user.UserRoleDO;
import com.yihu.mysql.query.BaseJpaService;
import com.yihu.utils.security.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Service - SAAS
 * Created by progr1mmer on 2018/8/14.
 */
@Service
public class SaasService extends BaseJpaService<SaasDO, SaasDao> {

    @Autowired
    private SaasDao saasDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private SaasDefaultModuleFunctionDao saasDefaultModuleFunctionDao;
    @Autowired
    private SaasModuleFunctionDao roleModuleFunctionDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UserRoleDao userRoleDao;

    @Transactional
    public SaasDO save(SaasDO saas, UserDO user) {
        //初始化租户信息
        String saasId = getCode();
        String userId = getCode();
        saas.setId(saasId);
        saas.setManager(userId);
        //初始化角色
        RoleDO roleDO = new RoleDO();
        roleDO.setName(saas.getName() + "管理员");
        roleDO.setRemark("Saas初始化分配");
        roleDO.setSystem(false);
        //初始化租户管理员
        user.setId(userId);
        user.setEnabled(true);
        user.setLocked(false);
        user.setSalt(randomString(5));
        String password = user.getPassword();
        if (StringUtils.isEmpty(password)) {
            password = user.getIdcard().substring(0, 5);
        }
        user.setPassword(MD5.md5Hex(password + "{" + user.getSalt() + "}"));
        //初始化管理员角色
        UserRoleDO userRoleDO = new UserRoleDO();
        userRoleDO.setUserId(user.getId());
        userRoleDO.setRoleId(roleDO.getId());
        //初始化租户默认模块
        List<SaasDefaultModuleFunctionDO> saasDefaultModuleDOS = saasDefaultModuleFunctionDao.findBySaasType(saas.getType());
        List<SaasModuleFunctionDO> roleModuleFunctionDOS = new ArrayList<>();
        saasDefaultModuleDOS.forEach(item -> {
            SaasModuleFunctionDO saasModuleFunctionDO = new SaasModuleFunctionDO();
            saasModuleFunctionDO.setSaasId(saasId);
            saasModuleFunctionDO.setModuleId(item.getModuleId());
            saasModuleFunctionDO.setFunctionId(item.getFunctionId());
            roleModuleFunctionDOS.add(saasModuleFunctionDO);
        });
        //保存数据
        saas = saasDao.save(saas);
        roleDao.save(roleDO);
        userDao.save(user);
        userRoleDao.save(userRoleDO);
        roleModuleFunctionDao.save(roleModuleFunctionDOS);
        return saas;
    }

    @Transactional
    public void delete(String ids) {
        for (String id : ids.split(",")) {
            SaasDO saas = saasDao.findById(id);
            saas.setStatus(SaasDO.Status.delete);
        }
    }

}
