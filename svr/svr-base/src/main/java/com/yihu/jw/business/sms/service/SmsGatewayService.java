package com.yihu.jw.business.sms.service;

import com.yihu.jw.business.sms.dao.SmsGatewayDao;
import com.yihu.jw.business.sms.model.BaseSmsGateway;
import com.yihu.jw.exception.ApiException;
import com.yihu.jw.exception.code.ExceptionCode;
import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.jw.rm.base.BaseSmsRequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * Created by chenweida on 2017/5/22.
 */
@Service
public class SmsGatewayService extends BaseJpaService<BaseSmsGateway, SmsGatewayDao> {
    @Autowired
    private SmsGatewayDao smsGatewayDao;

    @Transactional
    public BaseSmsGateway createSmsGateway(BaseSmsGateway smsGateway) throws ApiException {
        if (StringUtils.isEmpty(smsGateway.getCode())) {
            throw new ApiException(BaseSmsRequestMapping.SmsGateway.message_fail_code_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(smsGateway.getName())) {
            throw new ApiException(BaseSmsRequestMapping.SmsGateway.message_fail_name_is_null, ExceptionCode.common_error_params_code);
        }
        BaseSmsGateway smsGatewayTmp = smsGatewayDao.findByName(smsGateway.getName());
        if (smsGatewayTmp != null) {
            throw new ApiException(BaseSmsRequestMapping.SmsGateway.message_fail_name_exist, ExceptionCode.common_error_params_code);
        }
        return smsGatewayDao.save(smsGateway);
    }

    @Transactional
    public BaseSmsGateway updateSmsGateway(BaseSmsGateway smsGateway) {
        if (StringUtils.isEmpty(smsGateway.getCode())) {
            throw new ApiException(BaseSmsRequestMapping.SmsGateway.message_fail_code_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(smsGateway.getName())) {
            throw new ApiException(BaseSmsRequestMapping.SmsGateway.message_fail_name_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(smsGateway.getId())) {
            throw new ApiException(BaseSmsRequestMapping.SmsGateway.message_fail_id_is_null, ExceptionCode.common_error_params_code);
        }
        BaseSmsGateway smsGatewayTmp = smsGatewayDao.findByNameExcludeCode(smsGateway.getName(), smsGateway.getCode());
        if (smsGatewayTmp != null) {
            throw new ApiException(BaseSmsRequestMapping.SmsGateway.message_fail_name_exist, ExceptionCode.common_error_params_code);
        }
        return smsGatewayDao.save(smsGateway);
    }

    public BaseSmsGateway findByCode(String code) {
        BaseSmsGateway smsGateway = smsGatewayDao.findByCode(code);
        if (smsGateway == null) {
            throw new ApiException(BaseSmsRequestMapping.SmsGateway.message_fail_code_no_exist, ExceptionCode.common_error_params_code);
        }
        return smsGateway;
    }

    @Transactional
    public void deleteSmsGateway(String code) {
        BaseSmsGateway smsGateway = smsGatewayDao.findByCode(code);
        if (smsGateway == null) {
            throw new ApiException(BaseSmsRequestMapping.SmsGateway.message_fail_code_no_exist, ExceptionCode.common_error_params_code);
        }
        smsGateway.setStatus(-1);
    }
}
