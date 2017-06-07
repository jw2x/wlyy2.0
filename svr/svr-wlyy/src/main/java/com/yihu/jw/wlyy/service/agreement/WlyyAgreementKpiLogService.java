package com.yihu.jw.wlyy.service.agreement;

import com.yihu.jw.mysql.query.BaseJpaService;
import com.yihu.jw.restmodel.common.CommonContants;
import com.yihu.jw.restmodel.exception.ApiException;
import com.yihu.jw.restmodel.wlyy.agreement.WlyyAgreementContants;
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
            throw new ApiException(WlyyAgreementContants.Agreement.message_fail_saasId_is_null, CommonContants.common_error_params_code);
        }
        //Saas saas = saasService.findByCode(saasId);
        //if(saas==null){
        //    throw new ApiException(BaseContants.Saas.message_fail_code_no_exist, CommonContants.common_error_params_code);
        //}
        if (StringUtils.isEmpty(wlyyAgreementKpiLog.getPatientCode())) {
            throw new ApiException(WlyyAgreementContants.AgreementKpi.message_fail_patientCode_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyyAgreementKpiLog.getSignCode())) {
            throw new ApiException(WlyyAgreementContants.AgreementKpi.message_fail_signCode_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyyAgreementKpiLog.getKpiCode())) {
            throw new ApiException(WlyyAgreementContants.AgreementKpi.message_fail_kpiCode_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyyAgreementKpiLog.getAgreementCode())) {
            throw new ApiException(WlyyAgreementContants.AgreementKpi.message_fail_agreementCode_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyyAgreementKpiLog.getKpiName())) {
            throw new ApiException(WlyyAgreementContants.AgreementKpi.message_fail_kpiName_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyyAgreementKpiLog.getCode())) {
            throw new ApiException(WlyyAgreementContants.AgreementKpi.message_fail_code_is_null, CommonContants.common_error_params_code);
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
