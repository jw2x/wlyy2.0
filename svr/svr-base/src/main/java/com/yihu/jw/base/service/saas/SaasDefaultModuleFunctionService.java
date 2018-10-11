package com.yihu.jw.base.service.saas;

import com.yihu.jw.base.dao.saas.SaasDefaultModuleFunctionDao;
import com.yihu.jw.base.dao.saas.SaasTypeDictDao;
import com.yihu.jw.entity.base.saas.SaasDefaultModuleFunctionDO;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service - Saas默认模块功能
 * Created by progr1mmer on 2018/8/17.
 */
@Service
public class SaasDefaultModuleFunctionService extends BaseJpaService<SaasDefaultModuleFunctionDO, SaasDefaultModuleFunctionDao> {
    @Autowired
    private SaasDefaultModuleFunctionDao saasDefaultModuleFunctionDao;

    public void deleteBySaasType(Integer saasType) {
        saasDefaultModuleFunctionDao.deleteBySaasType(saasType);
    }
}
