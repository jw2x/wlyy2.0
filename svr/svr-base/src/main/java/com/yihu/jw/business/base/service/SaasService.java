package com.yihu.jw.business.base.service;

import com.yihu.jw.business.base.dao.SaasDao;
import com.yihu.jw.business.base.model.Saas;
import com.yihu.jw.exception.ApiException;
import com.yihu.jw.exception.code.ExceptionCode;
import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.jw.rm.base.BaseRequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * Created by chenweida on 2017/5/19.
 */
@Service
public class SaasService extends BaseJpaService<Saas, SaasDao> {
    @Autowired
    private SaasDao saasDao;

    @Transactional
    public Saas createSaas(Saas saas) throws ApiException {
        if (StringUtils.isEmpty(saas.getCode())) {
            throw new ApiException(BaseRequestMapping.Saas.message_fail_code_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(saas.getName())) {
            throw new ApiException(BaseRequestMapping.Saas.message_fail_name_is_null, ExceptionCode.common_error_params_code);
        }
        Saas saasTmp = saasDao.findByName(saas.getName());
        if (saasTmp != null) {
            throw new ApiException(BaseRequestMapping.Saas.message_fail_name_exist, ExceptionCode.common_error_params_code);
        }
        return saasDao.save(saas);
    }

    @Transactional
    public Saas updateSaas(Saas saas) {
        if (StringUtils.isEmpty(saas.getCode())) {
            throw new ApiException(BaseRequestMapping.Saas.message_fail_code_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(saas.getName())) {
            throw new ApiException(BaseRequestMapping.Saas.message_fail_name_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(saas.getId())) {
            throw new ApiException(BaseRequestMapping.Saas.message_fail_id_is_null, ExceptionCode.common_error_params_code);
        }
        Saas saasTmp = saasDao.findByNameExcludeCode(saas.getName(), saas.getCode());
        if (saasTmp != null) {
            throw new ApiException(BaseRequestMapping.Saas.message_fail_name_exist, ExceptionCode.common_error_params_code);
        }
        return saasDao.save(saas);
    }

    public Saas findByCode(String code) {
        Saas saas = saasDao.findByCode(code);
        if (saas == null) {
            throw new ApiException(BaseRequestMapping.Saas.message_fail_code_no_exist, ExceptionCode.common_error_params_code);
        }
        return saas;
    }

    @Transactional
    public void deleteSaas(String code) {
        Saas saas = saasDao.findByCode(code);
        if (saas == null) {
            throw new ApiException(BaseRequestMapping.Saas.message_fail_code_no_exist, ExceptionCode.common_error_params_code);
        }
        saas.setStatus(-1);
    }

    public Saas findByName(String cityName) {
        return saasDao.findByName(cityName);
    }
}
