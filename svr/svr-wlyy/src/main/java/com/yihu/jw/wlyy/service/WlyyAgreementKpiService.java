package com.yihu.jw.wlyy.service;

import com.yihu.jw.mysql.query.BaseJpaService;
import com.yihu.jw.restmodel.common.CommonContants;
import com.yihu.jw.restmodel.exception.ApiException;
import com.yihu.jw.restmodel.wlyy.WlyyContants;
import com.yihu.jw.wlyy.dao.WlyyAgreementKpiDao;
import com.yihu.jw.wlyy.entity.WlyyAgreementKpi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.Transient;

/**
 * Created by Administrator on 2017/6/1 0001.
 */
@Service
public class WlyyAgreementKpiService extends BaseJpaService<WlyyAgreementKpi, WlyyAgreementKpiDao> {

    @Autowired
    private WlyyAgreementKpiDao wlyyAgreementKpiDao;

    @Transient
    public WlyyAgreementKpi create(WlyyAgreementKpi wlyyAgreementKpi) {
        if (StringUtils.isEmpty(wlyyAgreementKpi.getCode())) {
            throw new ApiException(WlyyContants.AgreementKpi.message_fail_code_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyyAgreementKpi.getKpiName())) {
            throw new ApiException(WlyyContants.AgreementKpi.message_fail_kpiName_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyyAgreementKpi.getType())){
            throw new ApiException(WlyyContants.AgreementKpi.message_fail_type_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyyAgreementKpi.getStatus())) {
            throw new ApiException(WlyyContants.AgreementKpi.message_fail_status_is_null, CommonContants.common_error_params_code);
        }
        return wlyyAgreementKpiDao.save(wlyyAgreementKpi);
    }

    @Transient
    public WlyyAgreementKpi update(WlyyAgreementKpi wlyyAgreementKpi) {
        String code = wlyyAgreementKpi.getCode();
        if (StringUtils.isEmpty(code)) {
            throw new ApiException(WlyyContants.AgreementKpi.message_fail_code_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyyAgreementKpi.getKpiName())) {
            throw new ApiException(WlyyContants.AgreementKpi.message_fail_kpiName_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyyAgreementKpi.getType())){
            throw new ApiException(WlyyContants.AgreementKpi.message_fail_type_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyyAgreementKpi.getStatus())) {
            throw new ApiException(WlyyContants.AgreementKpi.message_fail_status_is_null, CommonContants.common_error_params_code);
        }
        Long id = wlyyAgreementKpi.getId();
        if (StringUtils.isEmpty(id)) {
            throw new ApiException(WlyyContants.Agreement.message_fail_id_is_null, CommonContants.common_error_params_code);
        }
        WlyyAgreementKpi wlyyAgreementKpi1 = wlyyAgreementKpiDao.findById(id);
        if(wlyyAgreementKpi1==null){
            throw new ApiException(WlyyContants.Agreement.message_fail_wlyyAgreement_is_no_exist, CommonContants.common_error_params_code);
        }
        wlyyAgreementKpi1 = wlyyAgreementKpiDao.findCodeExcludeId(code,id);
        if(wlyyAgreementKpi1 !=null){
            throw new ApiException(WlyyContants.Agreement.message_fail_code_exist, CommonContants.common_error_params_code);
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
            throw new ApiException(WlyyContants.AgreementKpi.message_fail_wlyyAgreementKpi_is_no_exist, CommonContants.common_error_params_code);
        }
        wlyyAgreementKpi.setStatus(-1);
        wlyyAgreementKpiDao.save(wlyyAgreementKpi);
    }
}
