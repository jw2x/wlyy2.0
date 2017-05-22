package com.yihu.jw.base.service.sms;

import com.yihu.jw.base.dao.sms.SmsGatewayDao;
import com.yihu.jw.base.model.sms.BaseSmsGateway;
import com.yihu.jw.mysql.query.BaseJpaService;
import com.yihu.jw.restmodel.base.BaseContants;
import com.yihu.jw.restmodel.common.CommonContants;
import com.yihu.jw.restmodel.exception.ApiException;
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
            throw new ApiException(BaseContants.SmsGateway.message_fail_code_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(smsGateway.getName())) {
            throw new ApiException(BaseContants.SmsGateway.message_fail_name_is_null, CommonContants.common_error_params_code);
        }
        BaseSmsGateway smsGatewayTmp = smsGatewayDao.findByName(smsGateway.getName());
        if (smsGatewayTmp != null) {
            throw new ApiException(BaseContants.SmsGateway.message_fail_name_exist, CommonContants.common_error_params_code);
        }
        return smsGatewayDao.save(smsGateway);
    }

    @Transactional
    public BaseSmsGateway updateSmsGateway(BaseSmsGateway smsGateway) {
        if (StringUtils.isEmpty(smsGateway.getCode())) {
            throw new ApiException(BaseContants.SmsGateway.message_fail_code_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(smsGateway.getName())) {
            throw new ApiException(BaseContants.SmsGateway.message_fail_name_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(smsGateway.getId())) {
            throw new ApiException(BaseContants.SmsGateway.message_fail_id_is_null, CommonContants.common_error_params_code);
        }
        BaseSmsGateway smsGatewayTmp = smsGatewayDao.findByNameExcludeCode(smsGateway.getName(), smsGateway.getCode());
        if (smsGatewayTmp != null) {
            throw new ApiException(BaseContants.SmsGateway.message_fail_name_exist, CommonContants.common_error_params_code);
        }
        return smsGatewayDao.save(smsGateway);
    }

    public BaseSmsGateway findByCode(String code) {
        BaseSmsGateway smsGateway = smsGatewayDao.findByCode(code);
        if (smsGateway == null) {
            throw new ApiException(BaseContants.SmsGateway.message_fail_code_no_exist, CommonContants.common_error_params_code);
        }
        return smsGateway;
    }

    @Transactional
    public void deleteSmsGateway(String code) {
        BaseSmsGateway smsGateway = smsGatewayDao.findByCode(code);
        if (smsGateway == null) {
            throw new ApiException(BaseContants.SmsGateway.message_fail_code_no_exist, CommonContants.common_error_params_code);
        }
        smsGateway.setStatus(-1);
    }
}
