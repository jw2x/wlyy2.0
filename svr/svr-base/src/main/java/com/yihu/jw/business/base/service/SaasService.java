package com.yihu.jw.business.base.service;

import com.yihu.jw.base.base.SaasDO;
import com.yihu.jw.business.base.dao.SaasDao;
import com.yihu.jw.exception.ApiException;
import com.yihu.jw.exception.code.ExceptionCode;
import com.yihu.jw.rm.base.BaseRequestMapping;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * Created by chenweida on 2017/5/19.
 */
@Service
public class SaasService extends BaseJpaService<SaasDO, SaasDao> {
    @Autowired
    private SaasDao saasDao;

    @Transactional
    public SaasDO createSaas(SaasDO saas) throws ApiException {
        if (StringUtils.isEmpty(saas.getId())) {
            throw new ApiException(BaseRequestMapping.Saas.message_fail_id_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(saas.getName())) {
            throw new ApiException(BaseRequestMapping.Saas.message_fail_name_is_null, ExceptionCode.common_error_params_code);
        }
        SaasDO saasTmp = saasDao.findByName(saas.getName());
        if (saasTmp != null) {
            throw new ApiException(BaseRequestMapping.Saas.message_fail_name_exist, ExceptionCode.common_error_params_code);
        }
        return saasDao.save(saas);
    }

    @Transactional
    public SaasDO updateSaas(SaasDO saas) {
        if (StringUtils.isEmpty(saas.getName())) {
            throw new ApiException(BaseRequestMapping.Saas.message_fail_name_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(saas.getId())) {
            throw new ApiException(BaseRequestMapping.Saas.message_fail_id_is_null, ExceptionCode.common_error_params_code);
        }
        SaasDO saasTmp = saasDao.findByNameExcludeId(saas.getName(), saas.getId());
        if (saasTmp != null) {
            throw new ApiException(BaseRequestMapping.Saas.message_fail_name_exist, ExceptionCode.common_error_params_code);
        }
        return saasDao.save(saas);
    }

    public SaasDO findById(String id) {
        SaasDO saas = saasDao.findById(id);
        if (saas == null) {
            throw new ApiException(BaseRequestMapping.Saas.message_fail_id_no_exist, ExceptionCode.common_error_params_code);
        }
        return saas;
    }

    @Transactional
    public void deleteSaas(String id) {
        SaasDO saas = saasDao.findById(id);
        if (saas == null) {
            throw new ApiException(BaseRequestMapping.Saas.message_fail_id_no_exist, ExceptionCode.common_error_params_code);
        }
        saas.setStatus(-1);
    }

    public SaasDO findByName(String cityName) {
        return saasDao.findByName(cityName);
    }
}
