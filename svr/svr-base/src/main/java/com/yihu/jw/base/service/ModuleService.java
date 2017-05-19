package com.yihu.jw.base.service;

import com.yihu.jw.base.dao.ModuleDao;
import com.yihu.jw.base.dao.ModuleDao;
import com.yihu.jw.base.model.Module;
import com.yihu.jw.base.model.Module;
import com.yihu.jw.mysql.query.BaseJpaService;
import com.yihu.jw.restmodel.base.BaseContants;
import com.yihu.jw.restmodel.common.CommonContants;
import com.yihu.jw.restmodel.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * Created by chenweida on 2017/5/19.
 */
@Service
public class ModuleService extends BaseJpaService<Module, ModuleDao> {
    @Autowired
    private ModuleDao moduleDao;

    @Transactional
    public Module createModule(Module module) throws ApiException {
        if (StringUtils.isEmpty(module.getCode())) {
            throw new ApiException(BaseContants.Module.message_fail_code_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(module.getName())) {
            throw new ApiException(BaseContants.Module.message_fail_name_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(module.getSaasId())) {
            throw new ApiException(BaseContants.Module.message_fail_saasid_is_null, CommonContants.common_error_params_code);
        }
        Module moduleTmp = moduleDao.findByName(module.getName());
        if (moduleTmp != null) {
            throw new ApiException(BaseContants.Module.message_fail_name_exist, CommonContants.common_error_params_code);
        }
        return moduleDao.save(module);
    }

    @Transactional
    public Module updateModule(Module module) {
        if (StringUtils.isEmpty(module.getCode())) {
            throw new ApiException(BaseContants.Module.message_fail_code_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(module.getName())) {
            throw new ApiException(BaseContants.Module.message_fail_name_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(module.getId())) {
            throw new ApiException(BaseContants.Module.message_fail_id_is_null, CommonContants.common_error_params_code);
        }
        Module moduleTmp = moduleDao.findByNameExcludeCode(module.getName(), module.getCode());
        if (moduleTmp != null) {
            throw new ApiException(BaseContants.Module.message_fail_name_exist, CommonContants.common_error_params_code);
        }
        return moduleDao.save(module);
    }

    public Module findByCode(String code) {
        Module module = moduleDao.findByCode(code);
        if (module == null) {
            throw new ApiException(BaseContants.Module.message_fail_code_no_exist, CommonContants.common_error_params_code);
        }
        return module;
    }

    @Transactional
    public void deleteModule(String code) {
        Module module = moduleDao.findByCode(code);
        if (module == null) {
            throw new ApiException(BaseContants.Module.message_fail_code_no_exist, CommonContants.common_error_params_code);
        }
        module.setStatus(-1);
    }
}
