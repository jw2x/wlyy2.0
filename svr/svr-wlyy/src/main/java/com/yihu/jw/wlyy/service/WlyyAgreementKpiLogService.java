package com.yihu.jw.wlyy.service;

import com.yihu.jw.mysql.query.BaseJpaService;
import com.yihu.jw.restmodel.common.CommonContants;
import com.yihu.jw.restmodel.exception.ApiException;
import com.yihu.jw.restmodel.wlyy.WlyyContants;
import com.yihu.jw.wlyy.dao.WlyyAgreementKpiLogDao;
import com.yihu.jw.wlyy.entity.WlyyAgreementKpiLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.Transient;

/**
 * Created by Administrator on 2017/6/1 0001.
 */
@Service
public class WlyyAgreementKpiLogService extends BaseJpaService<WlyyAgreementKpiLog, WlyyAgreementKpiLogDao> {

    @Autowired
    private WlyyAgreementKpiLogDao wlyyAgreementKpiLogDao;

    @Transient
    public WlyyAgreementKpiLog create(WlyyAgreementKpiLog wlyyAgreementKpiLog) {
        if (StringUtils.isEmpty(wlyyAgreementKpiLog.getPatientCode())) {
            throw new ApiException(WlyyContants.AgreementKpi.message_fail_patientCode_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyyAgreementKpiLog.getSignCode())) {
            throw new ApiException(WlyyContants.AgreementKpi.message_fail_signCode_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyyAgreementKpiLog.getKpiCode())) {
            throw new ApiException(WlyyContants.AgreementKpi.message_fail_kpiCode_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyyAgreementKpiLog.getAgreementCode())) {
            throw new ApiException(WlyyContants.AgreementKpi.message_fail_agreementCode_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyyAgreementKpiLog.getKpiName())) {
            throw new ApiException(WlyyContants.AgreementKpi.message_fail_kpiName_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyyAgreementKpiLog.getCode())) {
            throw new ApiException(WlyyContants.AgreementKpi.message_fail_code_is_null, CommonContants.common_error_params_code);
        }
        return wlyyAgreementKpiLogDao.save(wlyyAgreementKpiLog);
    }


    public WlyyAgreementKpiLog findByCode(String code) {
        return wlyyAgreementKpiLogDao.findByCode(code);
    }
}
