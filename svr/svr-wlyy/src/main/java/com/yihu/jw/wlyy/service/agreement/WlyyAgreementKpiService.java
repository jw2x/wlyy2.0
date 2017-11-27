package com.yihu.jw.wlyy.service.agreement;

import com.yihu.jw.exception.ApiException;
import com.yihu.jw.exception.code.ExceptionCode;
import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.jw.rm.wlyy.WlyyRequestMapping;
import com.yihu.jw.wlyy.agreement.WlyyAgreementDO;
import com.yihu.jw.wlyy.agreement.WlyyAgreementKpiDO;
import com.yihu.jw.wlyy.dao.agreement.WlyyAgreementKpiDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.Transient;
import java.util.Date;

/**
 * Created by Administrator on 2017/6/1 0001.
 */
@Service
public class WlyyAgreementKpiService extends BaseJpaService<WlyyAgreementKpiDO, WlyyAgreementKpiDao> {

    @Autowired
    private WlyyAgreementKpiDao wlyyAgreementKpiDao;

    @Autowired
    private WlyyAgreementService wlyyAgreementService;

    //@Autowired
    //private SaasService saasService;

    @Transient
    public WlyyAgreementKpiDO create(WlyyAgreementKpiDO wlyyAgreementKpi) {
        String saasId = wlyyAgreementKpi.getSaasId();
        if (StringUtils.isEmpty(saasId)) {
            throw new ApiException(WlyyRequestMapping.Agreement.message_fail_saasId_is_null, ExceptionCode.common_error_params_code);
        }
        //Saas saas = saasService.findByCode(saasId);
        //if(saas==null){
        //    throw new ApiException(BaseContants.Saas.message_fail_code_no_exist, ExceptionCode.common_error_params_code);
        //}
        //判断agreement是否存在
        String agreementCode = wlyyAgreementKpi.getAgreementCode();
        if (StringUtils.isEmpty(agreementCode)) {
            throw new ApiException(WlyyRequestMapping.AgreementKpi.message_fail_agreementId_is_null, ExceptionCode.common_error_params_code);
        }
        WlyyAgreementDO agreement = wlyyAgreementService.findById(agreementCode);
        if(agreement == null){
            throw new ApiException(WlyyRequestMapping.AgreementKpi.message_fail_agreement_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyyAgreementKpi.getKpiName())) {
            throw new ApiException(WlyyRequestMapping.AgreementKpi.message_fail_kpiName_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyyAgreementKpi.getType())){
            throw new ApiException(WlyyRequestMapping.AgreementKpi.message_fail_type_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyyAgreementKpi.getStatus())) {
            throw new ApiException(WlyyRequestMapping.AgreementKpi.message_fail_status_is_null, ExceptionCode.common_error_params_code);
        }
        //设置创建时间和修改时间
        Date date = new Date();
        wlyyAgreementKpi.setCreateTime(date);
        return wlyyAgreementKpiDao.save(wlyyAgreementKpi);
    }

    @Transient
    public WlyyAgreementKpiDO update(WlyyAgreementKpiDO wlyyAgreementKpi) {
        String saasId = wlyyAgreementKpi.getSaasId();
        if (StringUtils.isEmpty(saasId)) {
            throw new ApiException(WlyyRequestMapping.Agreement.message_fail_saasId_is_null, ExceptionCode.common_error_params_code);
        }
        //Saas saas = saasService.findByCode(saasId);
        //if(saas==null){
        //    throw new ApiException(BaseContants.Saas.message_fail_code_no_exist, ExceptionCode.common_error_params_code);
        //}
        //判断agreement是否存在
        String agreementCode = wlyyAgreementKpi.getAgreementCode();
        if (StringUtils.isEmpty(agreementCode)) {
            throw new ApiException(WlyyRequestMapping.AgreementKpi.message_fail_agreementId_is_null, ExceptionCode.common_error_params_code);
        }
        WlyyAgreementDO agreement = wlyyAgreementService.findById(agreementCode);
        if(agreement == null){
            throw new ApiException(WlyyRequestMapping.AgreementKpi.message_fail_agreement_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyyAgreementKpi.getKpiName())) {
            throw new ApiException(WlyyRequestMapping.AgreementKpi.message_fail_kpiName_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyyAgreementKpi.getType())){
            throw new ApiException(WlyyRequestMapping.AgreementKpi.message_fail_type_is_null, ExceptionCode.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyyAgreementKpi.getStatus())) {
            throw new ApiException(WlyyRequestMapping.AgreementKpi.message_fail_status_is_null, ExceptionCode.common_error_params_code);
        }
        String id = wlyyAgreementKpi.getId();
        if (StringUtils.isEmpty(id)) {
            throw new ApiException(WlyyRequestMapping.Agreement.message_fail_id_is_null, ExceptionCode.common_error_params_code);
        }
        WlyyAgreementKpiDO wlyyAgreementKpi1 = wlyyAgreementKpiDao.findById(id);
        if(wlyyAgreementKpi1==null){
            throw new ApiException(WlyyRequestMapping.Agreement.message_fail_wlyyAgreement_is_no_exist, ExceptionCode.common_error_params_code);
        }
        //设置创建时间和修改时间
        Date date = new Date();
        wlyyAgreementKpi.setCreateTime(wlyyAgreementKpi1.getCreateTime());

        return wlyyAgreementKpiDao.save(wlyyAgreementKpi);
    }

    public WlyyAgreementKpiDO findById(String id) {
        return wlyyAgreementKpiDao.findById(id);
    }

    @Transient
    public void delete(String id) {
        WlyyAgreementKpiDO wlyyAgreementKpi = findById(id);
        if(wlyyAgreementKpi==null){
            throw new ApiException(WlyyRequestMapping.AgreementKpi.message_fail_wlyyAgreementKpi_is_no_exist, ExceptionCode.common_error_params_code);
        }
        wlyyAgreementKpi.setStatus(-1);
        wlyyAgreementKpiDao.save(wlyyAgreementKpi);
    }
}
