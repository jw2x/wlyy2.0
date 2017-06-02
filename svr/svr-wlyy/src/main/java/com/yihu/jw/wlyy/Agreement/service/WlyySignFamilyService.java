package com.yihu.jw.wlyy.Agreement.service;

import com.yihu.jw.mysql.query.BaseJpaService;
import com.yihu.jw.restmodel.common.CommonContants;
import com.yihu.jw.restmodel.exception.ApiException;
import com.yihu.jw.restmodel.wlyy.WlyyContants;
import com.yihu.jw.wlyy.Agreement.dao.WlyySignFamilyDao;
import com.yihu.jw.wlyy.Agreement.entity.WlyySignFamily;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.Transient;

/**
 * Created by Administrator on 2017/6/2 0002.
 */
@Service
public class WlyySignFamilyService extends BaseJpaService<WlyySignFamily, WlyySignFamilyDao> {

    @Autowired
    private WlyySignFamilyDao wlyySignFamilyDao;

    @Transient
    public WlyySignFamily create(WlyySignFamily wlyySignFamily) {
        boolean b = canSaveOrUpdate(wlyySignFamily);
        if(b){
            return wlyySignFamilyDao.save(wlyySignFamily);
        }
        return null;
    }

    @Transient
    public WlyySignFamily update(WlyySignFamily wlyySignFamily) {
        boolean b = canSaveOrUpdate(wlyySignFamily);
        if(b){
            return wlyySignFamilyDao.save(wlyySignFamily);
        }
        return null;
    }

    public WlyySignFamily findByCode(String code) {
        return wlyySignFamilyDao.findByCode(code);
    }

    private boolean canSaveOrUpdate(WlyySignFamily wlyySignFamily){
        if (StringUtils.isEmpty(wlyySignFamily.getCode())) {
            throw new ApiException(WlyyContants.WlyySignFamily.message_fail_code_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyySignFamily.getType())) {
            throw new ApiException(WlyyContants.WlyySignFamily.message_fail_type_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyySignFamily.getName())) {
            throw new ApiException(WlyyContants.WlyySignFamily.message_fail_name_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyySignFamily.getIdcard())) {
            throw new ApiException(WlyyContants.WlyySignFamily.message_fail_idCard_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyySignFamily.getSsc())) {
            throw new ApiException(WlyyContants.WlyySignFamily.message_fail_ssc_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyySignFamily.getHospital())) {
            throw new ApiException(WlyyContants.WlyySignFamily.message_fail_hospital_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyySignFamily.getHospitalName())) {
            throw new ApiException(WlyyContants.WlyySignFamily.message_fail_hospitalName_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyySignFamily.getStatus())) {
            throw new ApiException(WlyyContants.WlyySignFamily.message_fail_status_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyySignFamily.getExpenses())) {
            throw new ApiException(WlyyContants.WlyySignFamily.message_fail_expense_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyySignFamily.getExpensesStatus())) {
            throw new ApiException(WlyyContants.WlyySignFamily.message_fail_expenseStatus_is_null, CommonContants.common_error_params_code);
        }
        if (StringUtils.isEmpty(wlyySignFamily.getAgreementCode())) {
            throw new ApiException(WlyyContants.WlyySignFamily.message_fail_agreementCode_is_null, CommonContants.common_error_params_code);
        }
        return true;
    }
}
