package com.yihu.jw.base.service;

import com.yihu.jw.base.dao.SaasDefaultModuleDao;
import com.yihu.jw.base.dao.SaasModuleDao;
import com.yihu.jw.base.dao.UserDao;
import com.yihu.jw.entity.base.saas.SaasDO;
import com.yihu.jw.base.dao.SaasDao;
import com.yihu.jw.entity.base.saas.SaasDefaultModuleDO;
import com.yihu.jw.entity.base.saas.SaasModuleDO;
import com.yihu.jw.entity.base.user.UserDO;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by chenweida on 2017/5/19.
 */
@Service
public class SaasService extends BaseJpaService<SaasDO, SaasDao> {

    @Autowired
    private SaasDao saasDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private SaasDefaultModuleDao saasDefaultModuleDao;
    @Autowired
    private SaasModuleDao saasModuleDao;

    @Transactional
    public SaasDO save(SaasDO saas, UserDO user) {
        user = userDao.save(user);
        saas.setManager(user.getId());
        saas = saasDao.save(saas);
        String saasId = saas.getId();
        List<SaasDefaultModuleDO> saasDefaultModuleDOS = saasDefaultModuleDao.findByType(saas.getType());
        List<SaasModuleDO> saasModuleDOS = new ArrayList<>();
        saasDefaultModuleDOS.forEach(item -> {
            SaasModuleDO saasModuleDO = new SaasModuleDO();
            saasModuleDO.setSaasId(saasId);
            saasModuleDO.setModuleId(item.getModuleId());
            saasModuleDOS.add(saasModuleDO);
        });
        saasModuleDao.save(saasModuleDOS);
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
