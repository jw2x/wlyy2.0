package com.yihu.jw.wlyy.service.agreement;

import com.yihu.jw.exception.ApiException;
import com.yihu.jw.exception.code.ExceptionCode;
import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.jw.rm.wlyy.WlyyRequestMapping;
import com.yihu.jw.wlyy.dao.agreement.WlyyAgreementKpiLogDao;
import com.yihu.jw.wlyy.entity.agreement.WlyyAgreementKpiLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.Transient;
import java.util.Date;

/**
 * Created by Administrator on 2017/6/1 0001.
 */
@Service
public class WlyyAgreementKpiLogService extends BaseJpaService<WlyyAgreementKpiLog, WlyyAgreementKpiLogDao> {

    @Autowired
    private WlyyAgreementKpiLogDao wlyyAgreementKpiLogDao;

    //@Autowired
    //private SaasService saasService;

    @Transient
    public WlyyAgreementKpiLog create(WlyyAgreementKpiLog wlyyAgreementKpiLog) {
        String saasId = wlyyAgreementKpiLog.getSaasId();
        if (StringUtils.isEmpty(saasId)) {
            throw new ApiException(WlyyRequestMapping.Agreement.message_fail_saasId_is_null, ExceptionCode.common_error_params_code);
        }
        //Saas saas = saasService.findByCode(saasId);
        //if(saas==null){
        //    throw new ApiException(BaseContants.Saas.message_fail_code_no_exist, ExceptionCode.common_error_params_code);
        //}
        if (StringUtils.isEmpty(wlyyAgreementKpiLog.getPatientCode())) {
            throw new ApiException(WlyyRequestMapping.AgreementKpi.message_fail_patientCode_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyyAgreementKpiLog.getSignCode())) {
            throw new ApiException(WlyyRequestMapping.AgreementKpi.message_fail_signCode_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyyAgreementKpiLog.getKpiCode())) {
            throw new ApiException(WlyyRequestMapping.AgreementKpi.message_fail_kpiCode_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyyAgreementKpiLog.getAgreementCode())) {
            throw new ApiException(WlyyRequestMapping.AgreementKpi.message_fail_agreementCode_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyyAgreementKpiLog.getKpiName())) {
            throw new ApiException(WlyyRequestMapping.AgreementKpi.message_fail_kpiName_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyyAgreementKpiLog.getCode())) {
            throw new ApiException(WlyyRequestMapping.AgreementKpi.message_fail_code_is_null, ExceptionCode.common_error_params_code);
        }
        //设置创建时间
        Date date = new Date();
        wlyyAgreementKpiLog.setCreateTime(date);
        return wlyyAgreementKpiLogDao.save(wlyyAgreementKpiLog);
    }


    public WlyyAgreementKpiLog findByCode(String code) {
        return wlyyAgreementKpiLogDao.findByCode(code);
    }
}
