package com.yihu.jw.wlyy.service.agreement;

import com.yihu.jw.mysql.query.BaseJpaService;
import com.yihu.jw.restmodel.common.CommonContants;
import com.yihu.jw.restmodel.exception.ApiException;
import com.yihu.jw.restmodel.wlyy.agreement.WlyyAgreementContants;
import com.yihu.jw.wlyy.dao.agreement.WlyyAgreementKpiDao;
import com.yihu.jw.wlyy.entity.agreement.WlyyAgreement;
import com.yihu.jw.wlyy.entity.agreement.WlyyAgreementKpi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.Transient;
import java.util.Date;

/**
 * Created by Administrator on 2017/6/1 0001.
 */
@Service
public class WlyyAgreementKpiService extends BaseJpaService<WlyyAgreementKpi, WlyyAgreementKpiDao> {

    @Autowired
    private WlyyAgreementKpiDao wlyyAgreementKpiDao;

    @Autowired
    private WlyyAgreementService wlyyAgreementService;

    //@Autowired
    //private SaasService saasService;

    @Transient
    public WlyyAgreementKpi create(WlyyAgreementKpi wlyyAgreementKpi) {
        if (StringUtils.isEmpty(wlyyAgreementKpi.getCode())) {
            throw new ApiException(WlyyAgreementContants.AgreementKpi.message_fail_code_is_null, CommonContants.common_error_params_code);
        }
        String saasId = wlyyAgreementKpi.getSaasId();
        if (StringUtils.isEmpty(saasId)) {
            throw new ApiException(WlyyAgreementContants.Agreement.message_fail_saasId_is_null, CommonContants.common_error_params_code);
        }
        //Saas saas = saasService.findByCode(saasId);
        //if(saas==null){
        //    throw new ApiException(BaseContants.Saas.message_fail_code_no_exist, CommonContants.common_error_params_code);
        //}
        //判断agreement是否存在
        String agreementCode = wlyyAgreementKpi.getAgreementCode();
        if (StringUtils.isEmpty(agreementCode)) {
            throw new ApiException(WlyyAgreementContants.AgreementKpi.message_fail_agreementCode_is_null, CommonContants.common_error_params_code);
        }
        WlyyAgreement agreement = wlyyAgreementService.findByCode(agreementCode);
        if(agreement == null){
            throw new ApiException(WlyyAgreementContants.AgreementKpi.message_fail_agreement_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyyAgreementKpi.getKpiName())) {
            throw new ApiException(WlyyAgreementContants.AgreementKpi.message_fail_kpiName_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyyAgreementKpi.getType())){
            throw new ApiException(WlyyAgreementContants.AgreementKpi.message_fail_type_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyyAgreementKpi.getStatus())) {
            throw new ApiException(WlyyAgreementContants.AgreementKpi.message_fail_status_is_null, CommonContants.common_error_params_code);
        }
        //设置创建时间和修改时间
        Date date = new Date();
        wlyyAgreementKpi.setCreateTime(date);
        wlyyAgreementKpi.setUpdaateTime(date);
        return wlyyAgreementKpiDao.save(wlyyAgreementKpi);
    }

    @Transient
    public WlyyAgreementKpi update(WlyyAgreementKpi wlyyAgreementKpi) {
        String code = wlyyAgreementKpi.getCode();
        if (StringUtils.isEmpty(code)) {
            throw new ApiException(WlyyAgreementContants.AgreementKpi.message_fail_code_is_null, CommonContants.common_error_params_code);
        }
        String saasId = wlyyAgreementKpi.getSaasId();
        if (StringUtils.isEmpty(saasId)) {
            throw new ApiException(WlyyAgreementContants.Agreement.message_fail_saasId_is_null, CommonContants.common_error_params_code);
        }
        //Saas saas = saasService.findByCode(saasId);
        //if(saas==null){
        //    throw new ApiException(BaseContants.Saas.message_fail_code_no_exist, CommonContants.common_error_params_code);
        //}
        //判断agreement是否存在
        String agreementCode = wlyyAgreementKpi.getAgreementCode();
        if (StringUtils.isEmpty(agreementCode)) {
            throw new ApiException(WlyyAgreementContants.AgreementKpi.message_fail_agreementCode_is_null, CommonContants.common_error_params_code);
        }
        WlyyAgreement agreement = wlyyAgreementService.findByCode(agreementCode);
        if(agreement == null){
            throw new ApiException(WlyyAgreementContants.AgreementKpi.message_fail_agreement_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyyAgreementKpi.getKpiName())) {
            throw new ApiException(WlyyAgreementContants.AgreementKpi.message_fail_kpiName_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyyAgreementKpi.getType())){
            throw new ApiException(WlyyAgreementContants.AgreementKpi.message_fail_type_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyyAgreementKpi.getStatus())) {
            throw new ApiException(WlyyAgreementContants.AgreementKpi.message_fail_status_is_null, CommonContants.common_error_params_code);
        }
        Long id = wlyyAgreementKpi.getId();
        if (StringUtils.isEmpty(id)) {
            throw new ApiException(WlyyAgreementContants.Agreement.message_fail_id_is_null, CommonContants.common_error_params_code);
        }
        WlyyAgreementKpi wlyyAgreementKpi1 = wlyyAgreementKpiDao.findById(id);
        if(wlyyAgreementKpi1==null){
            throw new ApiException(WlyyAgreementContants.Agreement.message_fail_wlyyAgreement_is_no_exist, CommonContants.common_error_params_code);
        }
        //设置创建时间和修改时间
        Date date = new Date();
        wlyyAgreementKpi.setCreateTime(wlyyAgreementKpi1.getCreateTime());
        wlyyAgreementKpi.setUpdaateTime(date);

        wlyyAgreementKpi1 = wlyyAgreementKpiDao.findCodeExcludeId(code,id);
        if(wlyyAgreementKpi1 !=null){
            throw new ApiException(WlyyAgreementContants.Agreement.message_fail_code_exist, CommonContants.common_error_params_code);
        }
        return wlyyAgreementKpiDao.save(wlyyAgreementKpi);
    }

    public WlyyAgreementKpi findByCode(String code) {
        return wlyyAgreementKpiDao.findByCode(code);
    }

    @Transient
    public void delete(String code) {
        WlyyAgreementKpi wlyyAgreementKpi = findByCode(code);
        if(wlyyAgreementKpi==null){
            throw new ApiException(WlyyAgreementContants.AgreementKpi.message_fail_wlyyAgreementKpi_is_no_exist, CommonContants.common_error_params_code);
        }
        wlyyAgreementKpi.setStatus(-1);
        wlyyAgreementKpiDao.save(wlyyAgreementKpi);
    }
}
